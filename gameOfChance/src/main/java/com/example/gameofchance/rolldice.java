package com.example.gameofchance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class rolldice extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(rolldice.class.getResource("rolldice-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Game Of Chance - Dice");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}