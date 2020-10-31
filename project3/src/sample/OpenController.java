package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OpenController {

    @FXML
    private VBox OPage;

    @FXML
    private ToggleGroup accountType;

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        //take data from open account form and add new account to database
        //System.out.println(bp.getCenter().get);

        RadioButton button = (RadioButton) accountType.getSelectedToggle();
        System.out.println(button.getText());

        //fname
        TextField fnameField = (TextField) OPage.getChildren().get(6);
        String fname = fnameField.getText();
        System.out.println(fname);

        //lname
        TextField lnameField = (TextField) OPage.getChildren().get(8);
        String lname = lnameField.getText();
        System.out.println(lname);

        //bal
        TextField balField = (TextField) OPage.getChildren().get(10);
        Double bal = Double.parseDouble(balField.getText());
        System.out.println(bal);

        //date
        TextField dateField = (TextField) OPage.getChildren().get(12);
        String dateStr = dateField.getText();
        String[] dateArr = dateStr.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        Date date = new Date(month, day, year);
        System.out.println(date.toString());

        //boolean
        TextField boolField = (TextField) OPage.getChildren().get(14);
        String boolStr = boolField.getText();
        boolean bool = Boolean.parseBoolean(boolStr);
        System.out.println(bool);

    }
}
