package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primary;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(new Controller(new Order()));
        Parent root = loader.load();
        primaryStage.setTitle("Sandwich Shop");
        primaryStage.setScene(new Scene(root, 800, 600));
        primary = primaryStage;
        primaryStage.show();
    }


    public static Stage getPrimary() {
        return primary;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
