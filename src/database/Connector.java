package database;

import org.jetbrains.annotations.Nullable;

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

//    @Nullable
    private ResultSet execute(String sql, Object[] varArr, boolean isUpdate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(varArr.length > 0) {
                for (int i=0; i<varArr.length; i++) {
                    if (Integer.class.equals(varArr[i].getClass())) {
                        preparedStatement.setInt(i+1, (Integer) varArr[i]);
                    }
                    else if (String.class.equals(varArr[i].getClass())) {
                        preparedStatement.setString(i+1, (String) varArr[i]);
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
            System.out.println(preparedStatement);
            if(isUpdate) {
                preparedStatement.executeUpdate();
                return null;
            }
            else {
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null) {
                    while (resultSet.next()) {
                        System.out.println("firstname: " + resultSet.getString("firstname"));
                        System.out.println("lastname: " + resultSet.getString("lastname"));
                    }
                }
                return null;
            }   
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
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

    public ResultSet executeWrapper(String sql, Object[] varArr, boolean isUpdate) {
        createConnection(Config.dbFullURL);
        ResultSet resultSet = execute(sql, varArr, isUpdate);
        closeConnection();
        return resultSet;
    }

    public boolean checkLogin(String username, String password) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            createConnection(Config.dbFullURL);
            preparedStatement = connection.prepareStatement(Const.CHECK_USER_EXIST);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("HERE!");
                result = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                closeConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String signUpUser() {
        String result = "Success";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            createConnection(Config.dbFullURL);
            preparedStatement = connection.prepareStatement(Const.INSERT_USER);

            // here logic required
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                closeConnection();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
