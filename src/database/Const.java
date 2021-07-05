package database;

public class Const {
    // database
    public static final String DATABASE_NAME = Config.dbName;
    // tables
    public static final String USERS_TABLE = "users";
    public static final String TASKS_TABLE = "tasks";
    // users columns
    public static final String USER_ID = "userid";
    public static final String USER_FIRSTNAME = "firstname";
    public static final String USER_LASTNAME = "lastname";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_LOCATION = "location";
    public static final String USER_GENDER = "gender";
    // tasks columns
    public static final String TASK_ID = "taskid";
    public static final String TASK_DATE = "datecreated";
    public static final String TASK_DESCRIPTION = "description";
    // foreign keys
    public static final String FK_USER_ID = "FK_userid";
    // create database/schema script
    public static final String CREATE_SCHEMA = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
    // create users table
    public static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS `" + USERS_TABLE + "` (" +
    "`" + USER_ID +"` INT NOT NULL AUTO_INCREMENT," +
    "`" + USER_FIRSTNAME + "` VARCHAR(50) NOT NULL," +
    "`" + USER_LASTNAME + "` VARCHAR(50) NOT NULL," +
    "`" + USER_USERNAME + "` VARCHAR(50) NOT NULL," +
    "`" + USER_PASSWORD + "` VARCHAR(50) NOT NULL," +
    "`" + USER_LOCATION + "` VARCHAR(50) NOT NULL," +
    "`" + USER_GENDER + "` VARCHAR(50) NOT NULL," +
    "PRIMARY KEY (`" + USER_ID + "`) USING BTREE " +
    ");";
    // create tasks table
    public static final String CREATE_TASKS = "CREATE TABLE IF NOT EXISTS `" + TASKS_TABLE + "` (" +
    "`" + TASK_ID +"` INT NOT NULL," +
    "`" + USER_ID + "` INT NOT NULL," +
    "`" + TASK_DATE + "` DATETIME NOT NULL," +
    "`" + TASK_DESCRIPTION + "` VARCHAR(50) NOT NULL," +
    "PRIMARY KEY (`" + TASK_ID + "`) USING BTREE, " +
    "CONSTRAINT `" + FK_USER_ID + "` FOREIGN KEY (`" + USER_ID + "`) REFERENCES `" + USERS_TABLE + "` (`" + USER_ID + "`)" +
    ");";
    // prepared scripts
    // inse
    public static final String INSERT_USER = "INSERT INTO " + Const.USERS_TABLE + "(" +
    Const.USER_FIRSTNAME + "," +
    Const.USER_LASTNAME + "," +
    Const.USER_USERNAME + "," +
    Const.USER_PASSWORD + "," +
    Const.USER_LOCATION + "," +
    Const.USER_GENDER + ")" +
    "VALUES(?,?,?,?,?,?)";
}
