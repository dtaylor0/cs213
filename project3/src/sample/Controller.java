package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Controller {

    @FXML
    private BorderPane bp;

    private VBox openPage;


    //shows open account menu when Open Account button is pressed
    @FXML
    public void openAccountPage(ActionEvent event) throws IOException {
        //load center pane from openAccount.fxml
        openPage = FXMLLoader.load(getClass().getResource("openAccount.fxml"));
        bp.setCenter(openPage);
    }
}
