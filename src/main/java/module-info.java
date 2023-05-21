module com.example.passwordcraft {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwordcraft to javafx.fxml;
    exports com.example.passwordcraft;
}