package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenController {

    @FXML
    private VBox OPage;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField bal;

    @FXML
    private TextField date;

    @FXML
    private TextField bool;

    @FXML
    private Text boolLabel;

    @FXML
    public void initialize() {
        accountType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton radioButton = (RadioButton) accountType.getSelectedToggle();
                String accType = radioButton.getText();
                if (accType.equals("Checking"))
                    boolLabel.setText("Direct Deposit (enter true/false)");
                else if (accType.equals("Savings")) {
                    boolLabel.setText("Loyal Customer (enter true/false)");
                }
                else if (accType.equals("Money Market")) {
                    boolLabel.setText("Number of withdrawals");
                }
            }
        });
    }

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

        //bal
        System.out.println(Double.parseDouble(bal.getText()));

        //date
        String dateStr = date.getText();
        String[] dateArr = dateStr.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        Date dateObj = new Date(month, day, year);
        System.out.println(dateObj.toString());

        //boolean
        String boolStr = bool.getText();
        int withdrawals = 0;
        boolean boolVal = false;
        if (accType.equals("Money Market")) {
            withdrawals = Integer.parseInt(boolStr);
            System.out.println(withdrawals);
        }
        else {
            boolVal = Boolean.parseBoolean(boolStr);
            System.out.println(boolVal);
        }
    }

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
    private void goHome(ActionEvent event) {
        changeScene("home.fxml");
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) OPage.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }
}
