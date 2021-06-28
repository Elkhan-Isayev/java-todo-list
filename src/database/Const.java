package database;

public class Const {
    // database
    public static final String DATABASE_NAME = "todolist";
    // tables
    public static final String USERS_TABLE = "users";
    public static final String TASKS_TABLE = "tasks";
    // users columns
    public static final String USERS_ID = "userid";
    public static final String USERS_FIRSTNAME = "firstname";
    public static final String USERS_LASTNAME = "lastname";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_LOCATION = "location";
    public static final String USERS_GENDER = "gender";
    // tasks columns
    public static final String TASKS_ID = "taskid";
    public static final String TASKS_DATE = "datecreated";
    public static final String TASKS_DESCRIPTION = "description";
    // scripts
    public static final String CREATE_SCHEMA = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
}
