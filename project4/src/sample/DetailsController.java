package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DetailsController {

    @FXML
    BorderPane bp;

    @FXML
    ListView orders;

    @FXML
    Button removeBtn;

    @FXML
    Button addBtn;

    @FXML
    Button backBtn;

    private Parent loadShop() {
        try {
            return FXMLLoader.load(getClass().getResource("sample.fxml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @FXML
    private void goToShop() {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadShop()), 800, 600);
        stage.setScene(scene);
    }

    @FXML
    private void removeItem() {
        //do stuff
    }

}
