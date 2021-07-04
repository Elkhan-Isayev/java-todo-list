package service;

interface ILogin {
    void login(String username, String password);
};

public class LoginService implements ILogin{
    @Override
    public void login(String username, String password) {
        // Check in database if the user with such username&password exists
        //...
    }
}
