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


    @FXML
    public void openAccountPage(ActionEvent event) throws IOException {
        System.out.println("hi");
        VBox openPage = FXMLLoader.load(getClass().getResource("openAccount.fxml"));
        bp.setCenter(openPage);


    }
}
