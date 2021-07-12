package controller;

import animation.Shaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.IUserService;
import service.implement.UserService;

import java.io.IOException;

public class UserController {
    private IUserService userService = new UserService();
    // sign up form
    public TextField signUpFirstName;
    public TextField signUpLastName;
    public TextField signUpUsername;
    public PasswordField signUpPassword;
    public CheckBox signUpCheckBoxMale;
    public CheckBox signUpCheckBoxFemale;
    public TextField signUpLocation;
    // login form
    public TextField loginUsername;
    public PasswordField loginPassword;
    // sign up btn on sign up page
    public Button signUpButton;
    // login/sign up btn on login page
    public Button loginLoginButtonHandler;
    public Button loginSignUpButton;

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private void redirectEngine(Node node, String targetPath) {
        try {
            // Close current window
            node.getScene().getWindow().hide();

            // Redirect user to signup window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(targetPath));
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // login btn on login page
    public void loginLoginButtonHandler() {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();

        if(!isEmpty(username) && !isEmpty(password)) {
            User user = new User();
            user.userUsername = username;
            user.userPassword = password;
            boolean isCorrectUsernamePassword = userService.loginUser(user);
            if(isCorrectUsernamePassword) {
                System.out.println("Welcome " + username);
                redirectEngine(loginLoginButtonHandler, "/view/addItem.fxml");
            }
            else {
                System.out.println("Incorrect credentials");
                Shaker loginShaker = new Shaker(loginUsername);
                loginShaker.shake();
                Shaker passwordShaker = new Shaker(loginPassword);
                passwordShaker.shake();
            }
        }
        else {
            System.out.println("All fields required");
        }
    }

    // sign up btn on login page
    public void loginSignUpButtonHandler() {
        redirectEngine(loginSignUpButton,"/view/signUp.fxml");
    }

    // sign up btn on sign up page
    public void signUpSignUpButtonHandler() {
        String firstName = signUpFirstName.getText().trim();
        String lastName = signUpLastName.getText().trim();
        String username = signUpUsername.getText().trim();
        String password = signUpPassword.getText().trim();
        String location = signUpLocation.getText().trim();
        String gender = "";
        if(signUpCheckBoxFemale.isSelected()) {
            gender = "Female";
        }
        else {
            gender = "Male";
        }
        Shaker firstNameShaker = new Shaker(signUpFirstName);
        Shaker lastNameShaker = new Shaker(signUpLastName);
        Shaker usernameShaker = new Shaker(signUpUsername);
        Shaker passwordShaker = new Shaker(signUpPassword);
        Shaker locationShaker = new Shaker(signUpLocation);
        if(!isEmpty(firstName)
                && !isEmpty(lastName)
                && !isEmpty(username)
                && !isEmpty(password)
                && !isEmpty(location)
                && !isEmpty(gender)) {
            User user = new User();
            user.userFirstname = firstName;
            user.userLastname = lastName;
            user.userUsername = username;
            user.userPassword = password;
            user.userLocation = location;
            user.userGender = gender;
            boolean isSuccess = userService.signUpUser(user);
            if(isSuccess) {
                redirectEngine(signUpButton, "/view/login.fxml");
            }
            else {
                System.out.println("Incorrect credentials");
                firstNameShaker.shake();
                lastNameShaker.shake();
                usernameShaker.shake();
                passwordShaker.shake();
                locationShaker.shake();
            }
        }
        else {
            System.out.println("All fields required");
            firstNameShaker.shake();
            lastNameShaker.shake();
            usernameShaker.shake();
            passwordShaker.shake();
            locationShaker.shake();
        }
    }

    @FXML
    void initialize() {

    }
}
