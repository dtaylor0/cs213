package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    BorderPane bp;

    @FXML
    ImageView imageView;

    @FXML
    ComboBox<String> sandwich;

    @FXML
    ListView<String> ingredients;

    @FXML
    ListView<String> selected;

    @FXML
    TextArea price;

    @FXML
    TextArea status;

    @FXML
    Button add_order;

    List<String> chicken_ingredients = Arrays.asList("Fried Chicken", "Spicy Sauce", "Pickles");
    List<String> beef_ingredients = Arrays.asList("Roast Beef", "Provolone Cheese", "Mustard");
    List<String> fish_ingredients = Arrays.asList("Grilled Snapper", "Cilantro", "Lime");
    List<String> extras = Arrays.asList("Mayo", "Ketchup", "Lettuce", "Onions", "Cheddar Cheese", "American Cheese", "Salt", "Bacon", "Pepper", "Avocado");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImageView("img/chicken.jpg");
        sandwich.getSelectionModel().select("Chicken");
        ingredients.getItems().addAll(extras);
        selected.getItems().addAll(chicken_ingredients);
        showPrice();
        showStatus("Status: Waiting for first order.\n");
    }

    public void setImageView(String path) {
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        imageView.setImage(img);
    }

    public void addIngredient() {
        String item = ingredients.getSelectionModel().getSelectedItem();
        if (item != null) {
            if (selected.getItems().size() < 9 && !selected.getItems().contains(item)) {
                selected.getItems().add(item);
            }
        }
        showPrice();
    }

    public void removeIngredient() {
        String item = selected.getSelectionModel().getSelectedItem();
        if (item != null) {
            if (extras.contains(item)) {
                selected.getItems().remove(item);
                selected.getSelectionModel().clearSelection();
            }
        }
        showPrice();
    }

    public void selectSandwich() {
        selected.getItems().clear();
        ingredients.getItems().clear();
        String temp = sandwich.getSelectionModel().getSelectedItem();
        switch (temp) {
            case "Chicken":
                setImageView("img/chicken.jpg");
                selected.getItems().addAll(chicken_ingredients);
                ingredients.getItems().addAll(extras);
                break;
            case "Beef":
                setImageView("img/beef.jpg");
                selected.getItems().addAll(beef_ingredients);
                ingredients.getItems().addAll(extras);
                break;
            case "Fish":
                setImageView("img/fish.jpg");
                selected.getItems().addAll(fish_ingredients);
                ingredients.getItems().addAll(extras);
                break;
            default:
                break;
        }
        showPrice();
    }

    public void showPrice() {
        ObservableList<String> currIngredients = selected.getItems();
        double currPrice = 0;
        double addon = 1.99;
        double chickenPrice = 8.99;
        double beefPrice = 10.99;
        double fishPrice = 12.99;
        int numBaseIngredients = 3;
        switch (sandwich.getSelectionModel().getSelectedItem()) {
            case "Chicken":
                currPrice = chickenPrice + addon * (currIngredients.size() - numBaseIngredients);
                break;
            case "Beef":
                currPrice = beefPrice + addon * (currIngredients.size() - numBaseIngredients);
                break;
            case "Fish":
                currPrice = fishPrice + addon * (currIngredients.size() - numBaseIngredients);
                break;
            default:
                break;
        }
        price.setText(String.format("$%.2f", currPrice));
    }

    public void addOrder() {
        //make sandwich with selected items
        //add to order list
        //reset selection
        showStatus("Sandwich added to order.\n");
    }

    private Parent loadDetails() {
        try {
            return FXMLLoader.load(getClass().getResource("details.fxml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @FXML
    private void goToDetails() {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadDetails()), 800, 600);
        stage.setScene(scene);
    }

    public void showStatus(String msg) {
        status.appendText(msg);
    }
}
