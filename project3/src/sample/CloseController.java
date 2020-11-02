package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class CloseController {

    @FXML
    private VBox CPage;

    @FXML
    private ToggleGroup accountType;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextArea output;

    @FXML
    private void closeAccount() throws IOException {
        RadioButton button = (RadioButton) accountType.getSelectedToggle();
        String accType = button.getText();

        //fname
        String firstName = fname.getText();

        //lname
        String lastName = lname.getText();

        Profile holder = new Profile(firstName, lastName);

        Date dummyDate = new Date(1, 1, 2001);

        AccountDatabase db = loadDB();

        boolean removeRes = false;
        switch (accType) {
            case "Savings": {
                Savings acct = new Savings(holder, 0, dummyDate, false);
                removeRes = db.remove(acct);
                break;
            }
            case "Checking": {
                Checking acct = new Checking(holder, 0, dummyDate, false);
                removeRes = db.remove(acct);
                break;
            }
            case "Money Market": {
                MoneyMarket acct = new MoneyMarket(holder, 0, dummyDate, 0);
                removeRes = db.remove(acct);
                break;
            }
            default: {
                break;
            }
        }

        if (removeRes) {
            output.appendText("Account closed and removed from the database.\n");
        }
        else {
            output.appendText("Account does not exist.\n");
        }

        writeDB(db);

    }

    private static AccountDatabase loadDB() throws FileNotFoundException {
        AccountDatabase db = new AccountDatabase();
        File f = new File(Path.path);
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
        Stage stage = (Stage) CPage.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadHome()), 900, 600);
        stage.setScene(scene);
    }
}
