package database;

import model.Task;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    private static final Connector instance = new Connector();
    private Connection connection;

    private Connector() {
    }

    public static Connector getInstance() {
        return instance;
    }

    private void createConnection(String url) {
        try {
            connection = DriverManager.getConnection(url, Config.dbUser, Config.dbPass);
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        }
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        }
    }

    /**
     * Creates the database, then its tables, on first run. Safe to call every
     * time the application starts because every statement uses IF NOT EXISTS.
     */
    public void setConfigs() {
        createConnection(Config.dbURL);
        executeStatement(Const.CREATE_SCHEMA);
        closeConnection();

        createConnection(Config.dbFullURL);
        executeStatement(Const.CREATE_USERS);
        executeStatement(Const.CREATE_TASKS);
        closeConnection();
    }

    public List<User> getUser(User user) {
        List<User> userList = new ArrayList<>();
        createConnection(Config.dbFullURL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(Const.CHECK_USER_EXIST)) {
            preparedStatement.setString(1, user.userUsername);
            preparedStatement.setString(2, user.userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User userListItem = new User();
                    userListItem.userId = resultSet.getInt(Const.USER_ID);
                    userListItem.userFirstname = resultSet.getString(Const.USER_FIRSTNAME);
                    userListItem.userLastname = resultSet.getString(Const.USER_LASTNAME);
                    userListItem.userUsername = resultSet.getString(Const.USER_USERNAME);
                    userListItem.userPassword = resultSet.getString(Const.USER_PASSWORD);
                    userListItem.userLocation = resultSet.getString(Const.USER_LOCATION);
                    userListItem.userGender = resultSet.getString(Const.USER_GENDER);
                    userList.add(userListItem);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        } finally {
            closeConnection();
        }
        return userList;
    }

    public boolean createUser(User user) {
        boolean result = false;
        createConnection(Config.dbFullURL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(Const.INSERT_USER)) {
            preparedStatement.setString(1, user.userFirstname);
            preparedStatement.setString(2, user.userLastname);
            preparedStatement.setString(3, user.userUsername);
            preparedStatement.setString(4, user.userPassword);
            preparedStatement.setString(5, user.userLocation);
            preparedStatement.setString(6, user.userGender);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean createTask(Task task) {
        boolean result = false;
        createConnection(Config.dbFullURL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(Const.INSERT_TASK)) {
            preparedStatement.setInt(1, task.userId);
            preparedStatement.setString(2, task.name);
            preparedStatement.setString(3, task.description);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Task> getTasks(int userId) {
        List<Task> taskList = new ArrayList<>();
        createConnection(Config.dbFullURL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(Const.SELECT_TASKS_BY_USER)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.taskId = resultSet.getInt(Const.TASK_ID);
                    task.userId = resultSet.getInt(Const.USER_ID);
                    task.name = resultSet.getString(Const.TASK_NAME);
                    task.description = resultSet.getString(Const.TASK_DESCRIPTION);
                    task.dateCreated = resultSet.getTimestamp(Const.TASK_DATE).toLocalDateTime();
                    taskList.add(task);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s%n%s%n", e.getSQLState(), e.getMessage());
        } finally {
            closeConnection();
        }
        return taskList;
    }
}
