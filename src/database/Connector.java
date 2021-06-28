package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connector {
    private static final Connector instance = new Connector();
    private Connection connection;
    private Statement statement;


    private Connector() {
    }

    public static Connector getInstance() {
        return instance;
    }

    public void createConnection() {
        final String url = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort;
        try (Connection connection = DriverManager.getConnection(url, Config.dbUser, Config.dbPass)) {
            this.connection = connection;
            if (connection != null) {
                System.out.println("Connected to the database!");
                statement = connection.createStatement();
                statement.executeUpdate(Const.CREATE_SCHEMA);
            } else {
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
}
