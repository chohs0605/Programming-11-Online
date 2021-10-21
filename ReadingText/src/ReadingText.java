import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingText {
    static ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Open file
        BufferedReader reader = new BufferedReader(new FileReader("ProgrammingHistory.txt"));

        // Read each line and add it into ArrayList
        String str;
        while((str = reader.readLine()) != null)
            data.add(str);
        reader.close();

        // Search
        ArrayList<Integer> idxArray = search("program");

        // Print all index
        System.out.print("Index :");
        for (Integer idx : idxArray)
            System.out.print(" " + idx);
    }

    static ArrayList<Integer> search(String word) {
        ArrayList<Integer> idxArray = new ArrayList<>();

        //Ignore case
        word = word.toLowerCase();

        // Add its index into idxArray if a line contains the word
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).toLowerCase().contains(word))
                idxArray.add(i);
        }

        // return Index array
        return idxArray;
    }
}