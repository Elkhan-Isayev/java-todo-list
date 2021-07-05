package service;

import database.Connector;
import database.Const;

import java.math.BigInteger;
import java.security.MessageDigest;

public class UserService implements IUserService {
    public void signUpUser(String firstname, String lastName, String username, String password, String location, String gender) {
        String[] insertVariables = new String[]{
                firstname,
                lastName,
                username,
                createHash(password),
                location,
                gender
        };
        Connector connector = Connector.getInstance();
        connector.executeWrapper(Const.INSERT_USER, insertVariables, false);
    }

    public void loginUser(String username, String password) {


    }

    private String createHash(String text) {
        // Convert password to md5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        }
        catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }
}
