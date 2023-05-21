package com.example.passwordcraft;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class PasswordDialogController {


    @FXML
    private Label passwordLabel;

    @FXML
    private Button closeButton;

    @FXML private TextField passwordTextField;

    @FXML private Button copyButton;






    public void initialize() {
        copyButton.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(passwordTextField.getText());
            clipboard.setContent(content);
        });
    }


    public void setPassword(String password) {
        passwordTextField.setText(password);
    }


}
