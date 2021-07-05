package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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

    private void execute(String sql, Object[] varArr, boolean isUpdate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(varArr.length > 0) {
                for (int i=0; i<varArr.length; i++) {
                    if (Integer.class.equals(varArr[i].getClass())) {
                        preparedStatement.setInt(i+1, (Integer) varArr[i]);
                    }
                    else if (String.class.equals(varArr[i].getClass())) {
                        preparedStatement.setObject(i+1, (String) varArr[i]);
                    }
                    else if (Double.class.equals(varArr[i].getClass())) {
                        preparedStatement.setDouble(i+1, (Double) varArr[i]);
                    }
                    else if(Float.class.equals(varArr[i].getClass())){
                        preparedStatement.setFloat(i+1, (Float) varArr[i]);
                    }
                    else {
                        throw new ClassNotFoundException();
                    }
                }
            }
            // Check if it's update or query
            if(isUpdate) {
                preparedStatement.executeUpdate();
            }
            else {
                preparedStatement.executeUpdate();
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
        // Create database
        createConnection(Config.dbURL);
            execute(Const.CREATE_SCHEMA, new Object[]{}, true);
        closeConnection();
        // Create Tasks & Users tables
        createConnection(Config.dbFullURL);
            execute(Const.CREATE_USERS, new Object[]{}, true);
            execute(Const.CREATE_TASKS, new Object[]{}, true);
        closeConnection();
    }

    public void executeWrapper(String sql, Object[] varArr, boolean isUpdate) {
        createConnection(Config.dbFullURL);
            execute(sql, varArr, isUpdate);
        closeConnection();
    }
}
