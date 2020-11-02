package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class ShowController {

    @FXML
    private VBox SPage;

    @FXML
    private ToggleGroup printOptions;

    @FXML
    private Button printButton;

    @FXML
    private RadioButton unsorted;

    @FXML
    private RadioButton dateOpened;

    @FXML
    private RadioButton lastName;

    @FXML
    private TextArea textArea;

    @FXML
    public void printAccounts(ActionEvent event) throws IOException
    {
        //take data from open account form and add new account to database
        Object eventSrc = event.getSource();
        AccountDatabase db = loadDB();
        textArea.appendText("");
        if(eventSrc.equals(printButton))
        {
            unsorted.setToggleGroup(printOptions);
            dateOpened.setToggleGroup(printOptions);
            lastName.setToggleGroup(printOptions);
            RadioButton selectedRadioButton = (RadioButton) printOptions.getSelectedToggle();
            String toggleGroupValue = selectedRadioButton.getText();
            switch(toggleGroupValue)
            {
                case "Unsorted":
                    textArea.appendText(db.printAccounts());//create database and use the appropriate print methods
                    break;
                case "Sorted by Date Opened":
                    textArea.appendText(db.printByDateOpen());
                    break;
                case "Sorted by Last Name":
                    textArea.appendText(db.printByLastName());
                    break;
                default:
                    break;
            }
        }
    }

    private Parent loadHome() {
        try {
            return FXMLLoader.load(getClass().getResource("home.fxml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    private void goHome() {
        Stage stage = (Stage) SPage.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadHome()), 900, 600);
        stage.setScene(scene);
    }

    private static AccountDatabase loadDB() throws FileNotFoundException {
        AccountDatabase db = new AccountDatabase();
        File f = new File("./src/sample/txt/database.txt");
        Scanner sc = new Scanner(f);
        sc.useDelimiter("\\Z");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] values = line.split(",");
            String accType = values[0];
            String fname = values[1];
            String lname = values[2];
            double balance = Double.parseDouble(values[3]);
            String date = values[4];
            String[] dateArr = date.split("/");
            int month = Integer.parseInt(dateArr[0]);
            int day = Integer.parseInt(dateArr[1]);
            int year = Integer.parseInt(dateArr[2]);
            int withdrawals;
            boolean bool;
            switch (accType) {
                case "M": {
                    withdrawals = Integer.parseInt(values[5]);
                    MoneyMarket acct = new MoneyMarket(new Profile(fname, lname), balance, new Date(month, day, year), withdrawals);
                    db.add(acct);
                    break;
                }
                case "S": {
                    bool = Boolean.parseBoolean(values[5]);
                    Savings acct = new Savings(new Profile(fname, lname), balance, new Date(month, day, year), bool);
                    db.add(acct);
                    break;
                }
                case "C": {
                    bool = Boolean.parseBoolean(values[5]);
                    Checking acct = new Checking(new Profile(fname, lname), balance, new Date(month, day, year), bool);
                    db.add(acct);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        sc.close();
        System.out.println(db.printAccounts());
        return db;
    }
}