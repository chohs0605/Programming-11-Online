package com.example.projectnumber2wordcards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;

public class MainController {
    @FXML
    Button btnLogin;
    @FXML
    Button btnStart;
    @FXML
    Button btnSave;
    @FXML
    Button btnSaveNotKnow;
    @FXML
    Button btnExport;
    @FXML
    Button btnFilter;
    @FXML
    Button btnIdontknow;
    @FXML
    Button btnIknow;
    @FXML
    TextField textWord;
    @FXML
    TextArea textMeaning;
    @FXML
    TextArea textExample;
    @FXML
    ListView<String> listView;

    static String loginId = null;
    ObservableList<Word> words = FXCollections.observableArrayList();
    ObservableList<Word> knowList = FXCollections.observableArrayList();
    ObservableList<String> selectedItems;
    int displayIndex = 0;
    boolean initBtnAction = false;

    @FXML
    public void setBtnLogin(ActionEvent event) {
        Stage loginStage = new Stage();
        try {
            Parent stat = FXMLLoader.load(getClass().getResource("login.fxml"));
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(stat));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setBtnStart(ActionEvent event) throws IOException {
        // If not logged in, display message
        if (loginId == null) {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Text text = new Text("Please log in first");
            text.setFont(Font.font ("Arial", 20));
            VBox vbox = new VBox(text);
            vbox.setAlignment(Pos.CENTER);
            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();
            return;
        }

        // Enable all buttons
        btnSave.setDisable(false);
        btnExport.setDisable(false);
        btnIknow.setDisable(false);
        btnIdontknow.setDisable(false);

        // Load data from files
        loadWords_know();
        loadWords();
        setTableData();
        displayIndex = 0;
        displayWord(displayIndex);

        if (initBtnAction)
            return;
        initBtnAction = true;

        //export button
        btnExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                //Set extension filter for text files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(new Stage());
                if (file != null) {
                    //Write all selected friends to the file
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        for (String f : selectedItems) {
                            writer.write("Word: " + f + "\n");
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Create a listView for selecting multiple friends
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                selectedItems =  listView.getSelectionModel().getSelectedItems();
                // debugging: print the selected friends
                /*
                System.out.print("Selected: ");
                for(Word f : selectedItems){
                    System.out.print(" " + f.getEmail());
                }
                System.out.println();
                 */
            }
        });
    }

    @FXML
    public void save(ActionEvent event) throws IOException {
        saveUser();
    }

    void saveUser() {
        // save file <username>_words.txt
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(loginId + "_words.txt"));
            for (Word word : knowList)
                writer.write(word.getWord() + "/" + word.getMeaning() + "/" + word.getExample() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void iknow(ActionEvent event) {
        // find the word from words and add it into knowList
        String word = textWord.getText();
        if (word.isEmpty())
            return;

        for (int i = 0; i < words.size(); i++) {
            Word w = words.get(i);
            if (w.getWord().equals(word)) {
                // if the word exist in knowList, return
                boolean exist = false;
                for (Word k : knowList) {
                    if (k.getWord().equals(word)) {
                        exist = true;
                        break;
                    }
                }
                if (!exist)
                    knowList.add(w);

                break;
            }
        }

        //update listView color
        setTableData();
        if (displayIndex >= words.size() - 1)
            displayIndex = 0;
        else
            displayIndex++;
        displayWord(displayIndex);
    }

    @FXML
    public void idontknow(ActionEvent event) {
        // remove the word from knowList
        String word = textWord.getText();
        if (word.isEmpty())
            return;

        for (int i = 0; i < knowList.size(); i++) {
            Word w = knowList.get(i);
            if (w.getWord().equals(word)) {
                knowList.remove(i);
                break;
            }
        }
        //update listView color
        setTableData();
        if (displayIndex >= words.size() - 1)
            displayIndex = 0;
        else
            displayIndex++;
        displayWord(displayIndex);
    }

    @FXML
    public void exportSelection(ActionEvent event) {
        //empty
    }

    // Load data from a user file
    void loadWords_know() {
        File f = new File(loginId + "_words.txt");
        if (!f.exists())
            return;

        // clear know list
        knowList.clear();

        try {
            // load know words list from the file
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String str;
            while ((str = reader.readLine()) != null) {
                // if format in file is wrong, skip it
                String[] values = str.split("/");
                if (values.length != 3)
                    continue;
                // add friend into List
                knowList.add(new Word(values[0], values[1], values[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Load all words in words.txt files
       Clear existing data and insert them into words arrayList
     */
    void loadWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            String str;

            words.clear();
            while ((str = reader.readLine()) != null) {
                // if format in file is wrong, skip it
                String[] values = str.split("/");
                if (values.length != 3)
                    continue;
                // add friend into List
                words.add(new Word(values[0], values[1], values[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Display all words on listView */
    void setTableData() {
        // display all data into tableview
        ObservableList<String> tmpList = FXCollections.observableArrayList();
        for (Word w : words)
            tmpList.add(w.getWord());

        listView.setItems(tmpList);
        // If word is in knowList, color it in highlighted
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        }
                        else {
                            setText(item);
                            for (Word word : knowList) {
                                if (word.getWord().equals(item)) {
                                    setStyle("-fx-background-color: lawngreen;");
                                    break;
                                }
                                else {
                                    setStyle("");
                                }
                            }
                        }
                    }
                };
            }
       });
    }

    /* Display word / meaning / example on each textfield */
    void displayWord(int idx) {
        textWord.setText(words.get(idx).getWord());
        textMeaning.setText(words.get(idx).getMeaning());
        textExample.setText(words.get(idx).getExample());
    }
}