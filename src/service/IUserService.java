package service;

import model.User;

public interface IUserService {
    boolean signUpUser(User user);

    boolean loginUser(User user);
}
