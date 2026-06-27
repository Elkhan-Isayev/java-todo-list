package service.implement;

import database.Connector;
import model.User;
import service.IUserService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService implements IUserService {
    private final Connector connector = Connector.getInstance();

    @Override
    public boolean signUpUser(User user) {
        user.userPassword = createHash(user.userPassword);
        if (connector.getUser(user).isEmpty()) {
            return connector.createUser(user);
        }
        return false;
    }

    @Override
    public User authenticate(User user) {
        user.userPassword = createHash(user.userPassword);
        List<User> matches = connector.getUser(user);
        return matches.isEmpty() ? null : matches.get(0);
    }

    private String createHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, messageDigest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return text;
        }
    }
}
