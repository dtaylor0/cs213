package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ShowController {

    @FXML
    private VBox OPage;

    @FXML
    private ToggleGroup printOptions;

    @FXML
    public void printAccounts(ActionEvent event) throws IOException {
        //take data from open account form and add new account to database

    }
}
