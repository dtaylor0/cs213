package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

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
    private TextArea output;

    @FXML
    private CheckBox directDeposit;

    @FXML
    private CheckBox loyalCustomer;

    @FXML
    private TextField numWithdrawals;

    @FXML
    public void initialize() {
        accountType.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            RadioButton radioButton = (RadioButton) accountType.getSelectedToggle();
            String accType = radioButton.getText();
            switch (accType) {
                case "Savings": {
                    loyalCustomer.setDisable(false);
                    directDeposit.setDisable(true);
                    numWithdrawals.setDisable(true);
                    break;
                }
                case "Checking": {
                    loyalCustomer.setDisable(true);
                    directDeposit.setDisable(false);
                    numWithdrawals.setDisable(true);
                    break;
                }
                case "Money Market": {
                    loyalCustomer.setDisable(true);
                    directDeposit.setDisable(true);
                    numWithdrawals.setDisable(false);
                    break;
                }
                default: {
                    break;
                }
            }
        });
    }

    @FXML
    public void createAccount() throws IOException {
        //take data from open account form and add new account to database

        RadioButton button = (RadioButton) accountType.getSelectedToggle();
        String accType = button.getText();

        //fname
        String firstName = fname.getText();

        //lname
        String lastName = lname.getText();

        Profile holder = new Profile(firstName, lastName);

        //bal
        double balance = Double.parseDouble(bal.getText());

        //date
        String dateStr = date.getText();
        String[] dateArr = dateStr.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        Date dateObj = new Date(month, day, year);

        AccountDatabase db = loadDB();


        boolean addRes = false;
        switch (accType) {
            case "Savings": {
                Savings acct = new Savings(holder, balance, dateObj, loyalCustomer.isSelected());
                addRes = db.add(acct);
                break;
            }
            case "Checking": {
                Checking acct = new Checking(holder, balance, dateObj, directDeposit.isSelected());
                addRes = db.add(acct);
                break;
            }
            case "Money Market": {
                MoneyMarket acct = new MoneyMarket(holder, balance, dateObj, Integer.parseInt(numWithdrawals.getText()));
                addRes = db.add(acct);
                break;
            }
            default: {
                break;
            }
        }

        if (addRes) {
            output.appendText("Account opened and added to the database.\n");
        }
        else {
            output.appendText("Account is already in the database.\n");
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
        return db;
    }

    private static void writeDB(AccountDatabase db) throws IOException {
        FileWriter writer = new FileWriter("./src/sample/txt/database.txt");
        String dbStr = db.convertToTxt();
        writer.write(dbStr);
        writer.close();
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
        Stage stage = (Stage) OPage.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadHome()), 900, 600);
        stage.setScene(scene);
    }

    public static void main(String[] args) throws IOException {
        AccountDatabase myDB = loadDB();
        myDB.printByLastName();
        writeDB(myDB);
    }
}
