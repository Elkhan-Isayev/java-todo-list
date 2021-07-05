package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import service.IUserService;
import service.UserService;

public class SignUpController {
    private IUserService userService = new UserService();
    // sign up form
    public TextField signUpFirstName;
    public TextField signUpLastName;
    public TextField signUpUsername;
    public TextField signUpPassword;
    public CheckBox signUpCheckBoxMale;
    public CheckBox signUpCheckBoxFemale;
    public TextField signUpLocation;
    // buttons
    public Button signUpButton;

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void signUpButtonHandler() {
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
