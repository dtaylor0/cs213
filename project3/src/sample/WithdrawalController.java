package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WithdrawalController {

    @FXML
    private VBox OPage;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField amount;

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        //take data from open account form and add new account to database

        RadioButton button = (RadioButton) accountType.getSelectedToggle();
        String accType = button.getText();
        System.out.println(accType);

        //fname
        System.out.println(fname.getText());

        //lname
        System.out.println(lname.getText());

        //withdrawal amount
        System.out.println(Double.parseDouble(amount.getText()));
    }
}
