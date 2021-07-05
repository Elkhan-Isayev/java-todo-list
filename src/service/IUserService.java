package service;

public interface IUserService {
    void signUpUser(String firstname, String lastName, String username, String password, String location, String gender);

    void loginUser(String username, String password);
}
