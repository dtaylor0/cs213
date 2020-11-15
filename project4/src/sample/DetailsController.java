package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {

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

    Order order;

    public DetailsController(Order o) {
        order = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<OrderLine> ol = order.getOrderlines();
        for (int i = 0; i < ol.size(); i++) {
            orders.getItems().add(ol.get(i).toString());
        }
    }

    private Parent loadShop() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            loader.setController(new Controller(order));
            Parent root = loader.load();
            return root;
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
