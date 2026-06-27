package session;

import model.User;

/**
 * Holds the user that is currently logged in so that other screens
 * (for example the task list) know which account they belong to.
 */
public class Session {
    private static final Session instance = new Session();
    private User currentUser;

    private Session() {
    }

    public static Session getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void clear() {
        this.currentUser = null;
    }
}
