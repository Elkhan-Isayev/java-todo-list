package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public PasswordField loginPassword;
    public Button loginButton;
    public TextField loginUsername;
    public Button loginSignUpButton;

    public void loginButtonHandler() {
        System.out.println("User log in...");
    }

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            System.out.println("Login Clicked!");
        });
    }
    
}
