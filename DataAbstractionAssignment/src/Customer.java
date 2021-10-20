import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        //create default constructor
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        this.name = name;
        this.accountNumber = accountNumber;

        // deposit the first deposit to checking list
        if (checkDeposit > 0)
            deposit(checkDeposit, new Date(), CHECKING);

        // deposit the first deposit to saving list
        if (savingDeposit > 0)
            deposit(savingDeposit, new Date(), SAVING);
    }

    public double deposit(double amt, Date date, String account){
        // add deposit obj to deposit list
        deposits.add(new Deposit(amt, date, account));

        // remove amount from balance
        if (account.equals(CHECKING))
            this.checkBalance += amt;
        else
            this.savingBalance += amt;

        // return the balance after deposit;
        return account.equals(CHECKING) ? this.checkBalance : this.savingBalance;
    }
    public double withdraw(double amt, Date date, String account){
        // if a customer try to withdraw over overdraft restriction, it fails.
        if (checkOverdraft(amt, account)) {
            System.out.println("Withdraw failed. Overdraft!!!");
            return account.equals(CHECKING) ? this.checkBalance : this.savingBalance;
        }

        // add withdraw obj to withdraw list
        withdraws.add(new Withdraw(amt, date, account));

        // return the balance after withdraw;
        if (account.equals(CHECKING))
            this.checkBalance -= amt;
        else
            this.savingBalance -= amt;

        // return the balance after withdraw;
        return account.equals(CHECKING) ? this.checkBalance : this.savingBalance;
    }
    private boolean checkOverdraft(double amt, String account){
        double balance = account.equals(CHECKING) ? this.checkBalance : this.savingBalance;

        if (balance - amt < OVERDRAFT)
            return true;

        return false;
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

}
