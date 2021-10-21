package com.example.friendsbook;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    public void delFriend(ActionEvent event) {
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
                addFriend();
                break;
            case 2:
                delFriend();
                break;
        }
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputSchool.setDisable(true);
        inputEmail.setDisable(true);
        okay.setDisable(true);
    }

    // Create a new friend and insert it into List
    void addFriend() {
        String email = inputEmail.getText();
        if (email.length() > 3 && email.contains("@") && email.contains(".")) {
            for (Friend f : data) {
                if (f.getEmail().equals(email)) {
                    msg.setText("the email already exists!");
                    return;
                }
            }

            data.add(new Friend(inputLastName.getText(), inputFirstName.getText(), email, inputSchool.getText()));
            inputFirstName.clear();
            inputLastName.clear();
            inputSchool.clear();
            inputEmail.clear();
        }
        setTableData();
    }

    // remove a friend who matches with email from List
    void delFriend() {
        String email = inputEmail.getText();
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