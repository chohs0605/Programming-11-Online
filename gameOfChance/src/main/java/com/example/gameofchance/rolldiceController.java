package com.example.gameofchance;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Random;

public class rolldiceController {
    public Label lableMsg;
    public Button btnOne;
    public Button btnTwo;
    public Button btnThree;
    public Button btnFour;
    public Button btnFive;
    public Button btnSix;
    public TextField txtRolledNumber;
    public TextField txtResult;
    public TextField txtUserBet;
    public TextField txtAddMoney;
    public TextField txtUserNumber;
    public TextField txtBalance;
    static double balance = 0;
    static double bet = 0;

    public void simulate(ActionEvent actionEvent) {
        if (!checkBetBalance())
            return;

        lableMsg.setText("");

        String userNumber = ((Button)actionEvent.getSource()).getText();
        txtUserNumber.setText(userNumber);

        // roll dice: get random number
        Random  r = new Random();
        int rolledNum = r.nextInt(6) + 1;
        txtRolledNumber.setText(Integer.toString(rolledNum));

        /* check if user's number matches rolledNumber.
           If a user wins, earn the bet,
           otherwise subtract the bet from the user's money
         */
        if (Integer.parseInt(userNumber) == rolledNum) {
            txtResult.setText("Win");
            balance += bet;
        }
        else {
            txtResult.setText("Lose");
            balance -= bet;
        }

        txtBalance.setText(Double.toString(balance));
        if (balance == 0) {
            disableDiceButtons(true);
            lableMsg.setText("WARNING: Please add the money to continue the game");
        }
    }

    boolean checkBetBalance() {
        if (balance <= 0) {
            lableMsg.setText("WARNING: Please add the money");
            return false;
        }

        if (txtUserBet.getText().trim().isEmpty()) {
            lableMsg.setText("WARNING: Bet is empty. Please enter your bet for the game");
            return false;
        }

        double tmpbet = Double.parseDouble(txtUserBet.getText());
        if (tmpbet > balance)
            lableMsg.setText("WARNING: not enough money in your balance. Please add the money");
        else if (tmpbet <= 0)
            lableMsg.setText("WARNING: your bet must be over than 0");
        else {
            bet = tmpbet;
            return true;
        }
        return false;
    }

    public void addMoney(ActionEvent actionEvent) {
        if (txtAddMoney.getText().trim().isEmpty()) {
            lableMsg.setText("WARNING: Money is empty. Please enter your money for the game");
            return;
        }
        lableMsg.setText("");

        double money = Double.parseDouble(txtAddMoney.getText());
        balance += money;
        txtBalance.setText(Double.toString(balance));

        if (balance > 0)
            disableDiceButtons(false);
        else
            disableDiceButtons(true);

        txtAddMoney.setText("");
    }

    void disableDiceButtons(boolean ctrl) {
        if (ctrl) {
            btnOne.setDisable(true);
            btnTwo.setDisable(true);
            btnThree.setDisable(true);
            btnFour.setDisable(true);
            btnFive.setDisable(true);
            btnSix.setDisable(true);
        } else {
            btnOne.setDisable(false);
            btnTwo.setDisable(false);
            btnThree.setDisable(false);
            btnFour.setDisable(false);
            btnFive.setDisable(false);
            btnSix.setDisable(false);
        }
    }
}