import database.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load front
        if(getClass().getResource("view/login.fxml") != null) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/login.fxml")));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 700, 400));
            primaryStage.show();
        }
        // First config of database
        Connector connector = Connector.getInstance();
        connector.setConfigs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
