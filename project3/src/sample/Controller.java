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
import javafx.stage.FileChooser;
import java.io.File;

import java.io.IOException;

public class Controller {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox child;

    @FXML
    private Button mge_accts;

    @FXML
    private Button transactions;

    @FXML
    private Button btn_show;
    
    @FXML
    private Button btn_import;

    @FXML
    private Button import_db;

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
    	Stage stage = (Stage) bp.getScene().getWindow();
        Object eventSrc = event.getSource();
        if (eventSrc.equals(mge_accts)) {
            changeScene("manageAccounts.fxml");
        }
        else if (eventSrc.equals(transactions)) {
            changeScene("accountTransactions.fxml");
        }
        else if (eventSrc.equals(btn_show)) {
            changeScene("showAccounts.fxml");
        }
        else if (eventSrc.equals(import_db)) {
            changeScene("importDB.fxml");
        }
        else if (eventSrc.equals(btn_import))
        {
        	FileChooser fileChooser = new FileChooser();
        	File file = fileChooser.showOpenDialog(stage);
        	System.out.println(file.canRead());
        }
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }

}
