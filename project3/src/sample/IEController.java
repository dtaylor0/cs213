package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class IEController {

    @FXML
    private BorderPane bp;

    @FXML
    private Button importBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private TextArea output;

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
    private void handleLoadFXML(ActionEvent event) throws IOException {
        Object eventSrc = event.getSource();
        if (eventSrc.equals(exportBtn)) {
            output.setText("Database exported to project3/src/sample/txt/database.txt");
        }
        else if (eventSrc.equals(importBtn)) {

            String res = "";
            FileChooser fileChooser = new FileChooser();
            try
            {
                Stage stage = (Stage) bp.getScene().getWindow();
                File file2 = fileChooser.showOpenDialog(stage);// needs to be try-catch for null pointer exception
                Scanner sc = new Scanner(file2);
                while(sc.hasNextLine())
                    res += sc.nextLine() + "\n";
                sc.close();
            }
            catch(NullPointerException | FileNotFoundException e)
            {
                e.printStackTrace();
            }
            File file = new File("./src/sample/txt", "database.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(res);
            bw.close();
            output.setText("Database successfully imported.");
        }
    }


    @FXML
    private void goHome() {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadFXML("home.fxml")), 900, 600);
        stage.setScene(scene);
    }
}
