package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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

        if (firstName.equals("") || lastName.equals("")) {
            output.appendText("Must enter a first and last name.\n");
            return;
        }

        Profile holder = new Profile(firstName, lastName);

        //bal
        double balance;
        try {
            balance = Double.parseDouble(bal.getText());
        }
        catch (Exception e) {
            output.appendText("Bad input for starting balance.\n");
            return;
        }
        if (balance < 0) {
            output.appendText("Cannot have negative starting balance.\n");
            return;
        }

        //date
        String dateStr = date.getText();
        int month = 0;
        int day = 0;
        int year = 0;
        try {
            String[] dateArr = dateStr.split("/");
            month = Integer.parseInt(dateArr[0]);
            day = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
        }
        catch (Exception e) {

        }
        Date dateObj = new Date(month, day, year);
        if(!dateObj.isValid()) {
            output.appendText("Invalid date.\n");
            return;
        }

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

    private AccountDatabase loadDB() throws FileNotFoundException {
        AccountDatabase db = new AccountDatabase();
        try
        {
            File f = new File(Path.path);
            try
            {
                Scanner sc = new Scanner(f);
                try
                {
                    sc.useDelimiter("\\Z");
                    while (sc.hasNextLine())
                    {
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
                        switch (accType)
                        {
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
                }
                catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e)
                {
                    output.appendText("The database is not in the correct format\n");
                }
                sc.close();
            }
            catch(NullPointerException e)
            {
                output.appendText("Can't scan a file that is null.\n");
            }
        }
        catch(IOException e)
        {
            output.appendText("File could not be found.\n");
        }
        return db;
    }

    private void writeDB(AccountDatabase db) throws IOException {
        FileWriter writer = new FileWriter(Path.path);
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

}
