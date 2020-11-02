package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * controls manageAccounts.fxml
 @author Shyam Patel, Drew Taylor
 */
public class ManageController {

    @FXML
    private BorderPane bp;

    @FXML
    private Button btn_open;

    @FXML
    private Button btn_close;

    @FXML
    private Button home;

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
    private void handleLoadFXML(ActionEvent event) {
        Object eventSrc = event.getSource();
        if (eventSrc.equals(btn_open)) {
            changeScene("openAccount.fxml");
        }
        else if (eventSrc.equals(btn_close)) {
            changeScene("closeAccount.fxml");
        }
        else if (eventSrc.equals(home)) {
            changeScene("home.fxml");
        }
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene;
        scene = new Scene(Objects.requireNonNull(loadFXML(fxml_file)), 900, 600);
        stage.setScene(scene);
    }

}
