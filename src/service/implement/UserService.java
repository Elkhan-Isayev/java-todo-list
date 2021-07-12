package service.implement;

import database.Connector;
import database.Const;
import model.User;
import service.IUserService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class UserService implements IUserService {
    private Connector connector = Connector.getInstance();


    public boolean signUpUser(User user) {
        boolean result = false;
        user.userPassword = createHash(user.userPassword);
        if(connector.getUser(user).isEmpty()) {
            result = connector.createUser(user);
        }
        return result;
    }

    public boolean loginUser(User user) {
        boolean result = false;
        try {
            user.userPassword = createHash(user.userPassword);
            result = !connector.getUser(user).isEmpty();
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
