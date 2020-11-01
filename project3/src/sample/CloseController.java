package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CloseController {

    @FXML
    private VBox CPage;

    @FXML
    private Button home;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    private Parent loadFXML(String name) {
        try {
            return FXMLLoader.load(getClass().getResource(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    private void goHome(ActionEvent event) {
        changeScene("home.fxml");
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) CPage.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }
}
