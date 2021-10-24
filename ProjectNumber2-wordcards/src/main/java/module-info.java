module com.example.projectnumber2wordcards {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectnumber2wordcards to javafx.fxml;
    exports com.example.projectnumber2wordcards;
}