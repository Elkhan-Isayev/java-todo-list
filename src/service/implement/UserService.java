package service.implement;

import database.Connector;
import database.Const;
import service.IUserService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class UserService implements IUserService {
    private Connector connector = Connector.getInstance();


    public void signUpUser(String firstname, String lastName, String username, String password, String location, String gender) {
        String[] insertVariables = new String[]{
                firstname,
                lastName,
                username,
                createHash(password),
                location,
                gender
        };

        connector.executeWrapper(Const.INSERT_USER, insertVariables, true);
    }

    public boolean loginUser(String username, String password) {
        boolean result = false;
        try {
            result = connector.checkLogin(username, createHash(password));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
