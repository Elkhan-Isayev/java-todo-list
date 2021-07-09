package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.IUserService;
import service.implement.UserService;

import java.io.IOException;

public class UserController {
    private IUserService userService = new UserService();
    // sign up form
    public TextField signUpFirstName;
    public TextField signUpLastName;
    public TextField signUpUsername;
    public TextField signUpPassword;
    public CheckBox signUpCheckBoxMale;
    public CheckBox signUpCheckBoxFemale;
    public TextField signUpLocation;
    // login form
    public TextField loginUsername;
    public PasswordField loginPassword;
    // sign up btn on sign up page
    public Button signUpButton;
    // login/sign up btn on login page
    public Button loginButton;
    public Button loginSignUpButton;

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


    // login btn on login page
    public void loginLoginButtonHandler() {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();

        if(!isEmpty(username) && !isEmpty(password)) {
            boolean isCorrectUsernamePassword = userService.loginUser(username, password);
            System.out.println(isCorrectUsernamePassword);
        }
        else {
            System.out.println("All fields required");
        }
    }

    // sign up btn on login page
    public void loginSignUpButtonHandler() {
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

    // sign up btn on sign up page
    public void signUpSignUpButtonHandler() {
        String firstName = signUpFirstName.getText().trim();
        String lastName = signUpLastName.getText().trim();
        String username = signUpUsername.getText().trim();
        String password = signUpPassword.getText().trim();
        String location = signUpLocation.getText().trim();
        String gender = "Male";
        if(!isEmpty(firstName)
                && !isEmpty(lastName)
                && !isEmpty(username)
                && !isEmpty(password)
                && !isEmpty(location)
                && !isEmpty(gender)) {
            userService.signUpUser(
                    firstName,
                    lastName,
                    username,
                    password,
                    location,
                    "Male"
            );
        }
        else {
            System.out.println("All fields required");
        }
    }

    @FXML
    void initialize() {

    }
}
