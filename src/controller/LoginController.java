package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.IUserService;

import java.io.IOException;

public class LoginController {
    private IUserService userService;

    // login form
    public TextField loginUsername;
    public PasswordField loginPassword;
    // buttons
    public Button loginButton;
    public Button loginSignUpButton;

    public void loginButtonHandler() {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
//        if(!username.equals("") && !password.equals("")) {
//
//        }
//        else {
//            System.out.println("Username or Password empty. Please, fill all fields and try again.");
//        }
    }

    public void signUpButtonHandler() {
        // Close current window
        loginSignUpButton.getScene().getWindow().hide();
        // Redirect user to signup window
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/signUp.fxml"));
        try {
            loader.load();
            // Set new stage and show scene
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
    
}
