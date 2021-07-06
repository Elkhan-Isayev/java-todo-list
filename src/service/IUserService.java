package service;

public interface IUserService {
    void signUpUser(String firstname, String lastName, String username, String password, String location, String gender);

    boolean loginUser(String username, String password);
}
