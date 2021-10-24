package com.example.projectnumber2wordcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField textId;
    @FXML
    Button btnLogin;

    @FXML
    protected void setBtnLogin(ActionEvent event) {
        // Get username from a user
        String uname = textId.getText();
        if (uname.isEmpty()) {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Text text = new Text("Please Enter the username");
            text.setFont(Font.font ("Arial", 20));
            VBox vbox = new VBox(text);
            vbox.setAlignment(Pos.CENTER);
            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();
            return;
        }

        // set loginId to the user name
        MainController.loginId = uname;
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}