package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DepositController {

    @FXML
    private VBox DPage;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField amount;

    @FXML
    public void deposit(ActionEvent event) throws IOException {
        //take data from open account form and add new account to database

        RadioButton button = (RadioButton) accountType.getSelectedToggle();
        String accType = button.getText();

        //fname
        String firstName = fname.getText();

        //lname
        String lastName = lname.getText();

        //withdrawal amount
        Double depositAmt = Double.parseDouble(amount.getText());

        Profile holder = new Profile(firstName, lastName);
        Date dummyDate = new Date(1, 1, 2001);

        AccountDatabase db = loadDB();

        if (accType.equals("Savings")) {
            Savings acct = new Savings(holder, 0, dummyDate, false);
            db.deposit(acct, depositAmt);
        }
        else if (accType.equals("Checking")) {
            Checking acct = new Checking(holder, 0, dummyDate, false);
            db.deposit(acct, depositAmt);
        }
        else if (accType.equals("Money Market")) {
            MoneyMarket acct = new MoneyMarket(holder, 0, dummyDate, 0);
            db.deposit(acct, depositAmt);
        }
        writeDB(db);

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
            Double balance = Double.parseDouble(values[3]);
            String date = values[4];
            String[] dateArr = date.split("/");
            int month = Integer.parseInt(dateArr[0]);
            int day = Integer.parseInt(dateArr[1]);
            int year = Integer.parseInt(dateArr[2]);
            int withdrawals;
            boolean bool;
            if (accType.equals("M")) {
                withdrawals = Integer.parseInt(values[5]);
                MoneyMarket acct = new MoneyMarket(new Profile(fname, lname), balance, new Date(month, day, year), withdrawals);
                db.add(acct);
            }
            else if(accType.equals("S")) {
                bool = Boolean.parseBoolean(values[5]);
                Savings acct = new Savings(new Profile(fname, lname), balance, new Date(month, day, year), bool);
                db.add(acct);
            }
            else if(accType.equals("C")) {
                bool = Boolean.parseBoolean(values[5]);
                Checking acct = new Checking(new Profile(fname, lname), balance, new Date(month, day, year), bool);
                db.add(acct);
            }
        }
        sc.close();
        return db;
    }

    private static void writeDB(AccountDatabase db) throws IOException {
        FileWriter writer = new FileWriter("./src/sample/txt/database.txt");
        String dbStr = db.convertToTxt();
        writer.write(dbStr);
        writer.close();
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
        Stage stage = (Stage) DPage.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }
}
