package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Controller {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox child;

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
        //child.getChildren().addAll(
                //loadFXML("openAccount.fxml")//,
                //loadFXML("closeAccount.fxml"),
                //loadFXML("showAccounts.fxml")
        //);
        Node n = (Node) event.getSource();
        String id = n.getId();
        if (id.equals("btn_open")) {
            //show index 1 of vbox
            child.getChildren().set(0, loadFXML("openAccount.fxml"));
        }
        else if (id.equals("btn_close")) {
            //show index 2 of vbox
        }
        else if (id.equals("btn_show")) {
            //show index 3 of vbox
        }
    }

}
