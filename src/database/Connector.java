package database;

import java.sql.*;

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
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String sql, boolean isUpdate) {
        try {
            Statement statement = connection.createStatement();
            if(isUpdate) {
                statement.executeUpdate(sql);
            }
            else {
                statement.executeQuery(sql);
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConfigs() {
        setConfig(Config.dbURL, Const.CREATE_SCHEMA, true);
        // create bulk sql request
        String[] sqlBulk = {Const.CREATE_USERS, Const.CREATE_TASKS};
        setConfig((Config.dbURL + Config.dbName), sqlBulk, true);
    }


    private void setConfig(String connectionURL, String sql, boolean isUpdate) {
        createConnection(connectionURL);
        execute(sql, isUpdate);
        closeConnection();
    }

    private void setConfig(String connectionURL, String[] sqlBulk, boolean isUpdate) {
        createConnection(connectionURL);
        for (String sql : sqlBulk) {
            execute(sql, isUpdate);
        }
        closeConnection();
    }
}
