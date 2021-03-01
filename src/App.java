import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    public static Stage activeStage;
    public static UIController homeController;
    public static Scene homeScene;
    public static String theme = "LightTheme.css";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/Layout.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        BorderPane root = (BorderPane) loader.load(fxmlStream);
        // Create the Scene
        homeScene = new Scene(root);
        homeScene.getStylesheets().setAll(theme);
        // Set the Scene to the Stage
        stage.setScene(homeScene);
        // Set the Title to the Stage
        stage.setTitle("Enigma Simulator");
        // Display the Stage
        activeStage = stage;
        homeController = loader.getController();
        activeStage.show();

    }
}
