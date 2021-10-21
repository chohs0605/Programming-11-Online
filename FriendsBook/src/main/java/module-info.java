module com.example.friendsbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.friendsbook to javafx.fxml;
    exports com.example.friendsbook;
}