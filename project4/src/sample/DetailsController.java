package sample;

import javafx.event.ActionEvent;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {

    @FXML
    BorderPane bp;

    @FXML
    ListView<String> orders;

    @FXML
    Button removeBtn;

    @FXML
    Button addBtn;

    @FXML
    Button backBtn;

    @FXML
    Button fileBtn;

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



    private Parent loadHome() {
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
    private void goToShop() //edited
    {
        Main.getPrimary().setScene(new Scene(Objects.requireNonNull(loadHome()), 800, 600));
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void removeItem() {
        int i = orders.getSelectionModel().getSelectedIndex();
        if (i < 0) {
            return;
        }
        order.removeAt(i);
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
        totalPrice.setText(String.format("Total Price: $%.2f\n", order.totalPrice()));
    }

    @FXML
    private void exportOrder(ActionEvent event) throws IOException
    {
        Object eventSrc = event.getSource();
        File file = new File("./src/sample/OrderDetails.txt");
        if (!file.exists( ))
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        if (eventSrc.equals(fileBtn))
        {
            //method to write order details to file
            String res = order.toString();
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(res);
            bw.close();
        }
        updateOrders();
        String filePath = file.getAbsolutePath();
        for(int i = 0; i < filePath.length() - 1; i++)
        {
            if(filePath.charAt(i) == '\\' && filePath.charAt(i + 1) == '.')
                filePath = filePath.substring(0, i) + filePath.substring(i + 2);
        }
        totalPrice.appendText("Orders exported to:\n" + filePath);

    }

}
