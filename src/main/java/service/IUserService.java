package service;

import model.User;

public interface IUserService {
    boolean signUpUser(User user);

    /**
     * Returns the matching user when the credentials are valid, otherwise null.
     */
    User authenticate(User user);
}
