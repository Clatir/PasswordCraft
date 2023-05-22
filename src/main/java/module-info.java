module com.example.passwordcraft {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.passwordcraft to javafx.fxml;
    exports com.example.passwordcraft;
}