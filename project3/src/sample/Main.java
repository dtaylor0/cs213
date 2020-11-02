package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root));
        File file = new File(Path.path);
        if (!file.exists( ))
            file.createNewFile();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
