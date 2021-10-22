package com.example.friendsbook;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FriendsBookController {
    @FXML
    TableView table;
    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputLastName;
    @FXML
    TextField inputSchool;
    @FXML
    TextField inputEmail;
    @FXML
    Button okay;
    @FXML
    Label msg;

    // use obserableArrayList to manage list
    private final ObservableList<Friend> data = FXCollections.observableArrayList();
    // Tableview initialization field
    private boolean initTableView = false;
    // menu
    private int menu = 0;
    // selected items list
    private ObservableList<Friend> selectedItems;

    @FXML
    public void importFriends(ActionEvent event) throws IOException {
        // Open file chooser box
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)","*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            /* read all friends from the file, parse data, make friend obj, and add it into List */
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str;
            while ((str = reader.readLine()) != null) {
                // if format in file is wrong, skip it
                String[] values = str.split(",");
                if (values.length != 4)
                    continue;
                // add friend into List
                addFriend(values[0], values[1], values[2], values[3]);
            }
            reader.close();
        }
    }

    @FXML
    public void exportFriends(ActionEvent event) {
        // Create a listView for selecting multiple friends
        ListView<Friend> listView = new ListView<>();
        listView.setItems(data);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                selectedItems =  listView.getSelectionModel().getSelectedItems();
                // debugging: print the selected friends
                /*
                System.out.print("Selected: ");
                for(Friend f : selectedItems){
                    System.out.print(" " + f.getEmail());
                }
                System.out.println();
                 */
            }
        });

        // create export button
        Button btnExport = new Button("Export");
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
                        for (Friend f : selectedItems) {
                            writer.write(f.getFirstName() + "," +
                                    f.getLastName() + "," +
                                    f.getSchool() + "," +
                                    f.getEmail() + "\n");
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Create scene and append listview and export button
        Stage stage = new Stage();
        stage.setTitle("Select friends");
        HBox hbox = new HBox();
        hbox.getChildren().add(listView);
        hbox.getChildren().add(btnExport);

        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addNewFriend(ActionEvent event) {
        msg.setText("");
        inputFirstName.setDisable(false);
        inputLastName.setDisable(false);
        inputSchool.setDisable(false);
        inputEmail.setDisable(false);
        okay.setDisable(false);
        menu = 1;
    }

    @FXML
    public void deleteFriend(ActionEvent event) {
        msg.setText("");
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputSchool.setDisable(true);
        inputEmail.setDisable(false);
        okay.setDisable(false);
        menu = 2;
    }

    @FXML
    public void displayAllFriends(ActionEvent event) {
        msg.setText("");
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputSchool.setDisable(true);
        inputEmail.setDisable(true);
        okay.setDisable(true);
        setTableData();
        menu = 0;
    }

    @FXML
    public void okay(ActionEvent event) {
        switch (menu) {
            case 1:
                addFriend(inputFirstName.getText(),
                        inputLastName.getText(),
                        inputSchool.getText(),
                        inputEmail.getText());
                break;
            case 2:
                delFriend(inputEmail.getText());
                break;
        }
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputSchool.setDisable(true);
        inputEmail.setDisable(true);
        okay.setDisable(true);
    }

    // Create a new friend and insert it into List
    void addFriend(String firstName, String lastName, String school, String email) {
        if (email.length() > 3 && email.contains("@") && email.contains(".")) {
            for (Friend f : data) {
                if (f.getEmail().equals(email)) {
                    msg.setText("the email already exists!");
                    return;
                }
            }

            data.add(new Friend(lastName, firstName, email, school));
            inputFirstName.clear();
            inputLastName.clear();
            inputSchool.clear();
            inputEmail.clear();
        }
        setTableData();
    }

    // remove a friend who matches with email from List
    void delFriend(String email) {
        if (email.length() > 3 && email.contains("@") && email.contains(".")) {
            //find friend from the list
            Friend delf = null;
            for (Friend f : data) {
                if (f.getEmail().equals(email))
                    delf = f;
            }
            if (delf != null)
                data.remove(delf);
            else
                msg.setText("cannot find the email from the friends list : " + email);
        }
        inputEmail.clear();
        setTableData();
    }

    void setTableData() {
        if (!initTableView) {
            //create table with 3 columns : lName, fName, email
            TableColumn firstNameCol = new TableColumn("FirstName");
            firstNameCol.setMinWidth(100);
            firstNameCol.setCellValueFactory(new PropertyValueFactory<Friend, String>("firstName"));

            TableColumn lastNameCol = new TableColumn("LastName");
            lastNameCol.setMinWidth(100);
            lastNameCol.setCellValueFactory(new PropertyValueFactory<Friend, String>("lastName"));

            TableColumn emailCol = new TableColumn("Email");
            emailCol.setMinWidth(240);
            emailCol.setCellValueFactory(new PropertyValueFactory<Friend, String>("email"));

            table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

            table.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showPersonDetails(newValue)
            );

            // Set initialization of table view to true
            initTableView = true;
        }
        // display all data into tableview
        table.setItems(data);
    }

    public void showPersonDetails(Object obj) {
        if (obj == null)
            return;

        Label lable = new Label(((Friend)obj).toString());
        lable.setFont(new Font("Arial", 16));
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(lable);

        Scene secondScene = new Scene(secondaryLayout, 250, 150);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Friend Property");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(200);
        newWindow.setY(100);

        newWindow.show();
    }
}