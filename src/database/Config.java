package database;

public class Config {
    protected static final String dbHost = "127.0.0.1";
    protected static final String dbPort = "4300";
    protected static final String dbUser = "root";
    protected static final String dbPass = "root";
    protected static final String dbName = "todolist";
    protected static final String dbURL = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/";
}
