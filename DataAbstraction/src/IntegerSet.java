import java.util.ArrayList;

public class IntegerSet {
    int size = 0;
    ArrayList<Integer> numbers = new ArrayList<>();

    //Requires: int
    //Modifies: this
    //Effects: inserts integer into set unless it's already there, in which case do nothing
    public void insert(Integer num){
        if (!numbers.contains(num)) {
            numbers.add(num);
            size++;
        }
    }

    //Requires: int
    //Modifies: this
    //Effects: if the integer is in the set remove it, otherwise do nothing
    public void remove(Integer num){
        if (numbers.contains(num)) {
            numbers.remove(num);
            size--;
        }
    }
    //Effects: returns the size of the integer set
    public int size(){
        return size;
    }
    //Requires: int
    //Effects: returns true if num is in set, otherwise returns false
    public boolean contains(Integer num){
        return numbers.contains(num);
    }
}
