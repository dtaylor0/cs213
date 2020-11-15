package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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

    @FXML
    TextArea totalPrice;

    Order order;

    public DetailsController(Order o) {
        order = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateOrders();
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
        int i = orders.getSelectionModel().getSelectedIndex();
        if (i < 0) {
            return;
        }
        order.remove(i);
        updateOrders();
    }

    @FXML
    private void addDupe() {
        int i = orders.getSelectionModel().getSelectedIndex();
        if (i < 0) {
            return;
        }
        Sandwich s = order.getSandwich(i);
        order.add(new OrderLine(0, s, s.price()));
        updateOrders();
    }

    @FXML
    private void clear() {
        order = new Order();
        updateOrders();
    }

    private void updateOrders() {
        orders.getItems().clear();
        String lines[] = order.toString().split("\\r?\\n");
        orders.getItems().addAll(lines);
        showPrice();
    }

    private void showPrice() {
        totalPrice.setText(String.format("Total Price: $%.2f", order.totalPrice()));
    }

}
