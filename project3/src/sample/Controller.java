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

public class Controller {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox child;

    @FXML
    private Button btn_open;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_show;

    //shows open account menu when Open Account button is pressed
//    @FXML
//    public void openAccountPage(ActionEvent event) throws IOException {
//        //load center pane from openAccount.fxml
//        //openPage = FXMLLoader.load(getClass().getResource("openAccount.fxml"));
//
//
//    }

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
        else if (eventSrc.equals(btn_show)) {
            changeScene("showAccounts.fxml");
        }
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }

}
