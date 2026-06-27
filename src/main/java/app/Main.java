package app;

import database.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Make sure the database, schema and tables exist before the UI starts.
        Connector.getInstance().setConfigs();

        // Load the login screen.
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/view/login.fxml")));
        primaryStage.setTitle("TODO APP");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
