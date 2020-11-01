package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TransactionController {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox child;

    @FXML
    private Button deposit;

    @FXML
    private Button withdrawal;

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
        if (eventSrc.equals(deposit)) {
            changeScene("deposit.fxml");
        }
        else if (eventSrc.equals(withdrawal)) {
            changeScene("withdrawal.fxml");
        }
        else if (eventSrc.equals(home)) {
            changeScene("home.fxml");
        }
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }

}
