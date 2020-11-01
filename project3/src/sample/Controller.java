package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox child;

    @FXML
    private Button mge_accts;

    @FXML
    private Button transactions;

    @FXML
    private Button btn_show;
    
    @FXML
    private Button btn_import;

    @FXML
    private Button import_db;

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
    private void handleLoadFXML(ActionEvent event) throws FileNotFoundException, IOException {
    	Stage stage = (Stage) bp.getScene().getWindow();
        Object eventSrc = event.getSource();
        if (eventSrc.equals(mge_accts)) {
            changeScene("manageAccounts.fxml");
        }
        else if (eventSrc.equals(transactions)) {
            changeScene("accountTransactions.fxml");
        }
        else if (eventSrc.equals(btn_show)) {
            changeScene("showAccounts.fxml");
        }
        else if (eventSrc.equals(btn_import))
        {
        	String res = "";
            FileChooser fileChooser = new FileChooser();
            try
            {
            	File file2 = fileChooser.showOpenDialog(stage);// needs to be try-catch for null pointer exception
            	Scanner sc = new Scanner(file2);
            	while(sc.hasNextLine())
            		res += sc.nextLine() + "\n";
            	sc.close();
            }
            catch(NullPointerException e)
            {
            	e.printStackTrace();
            }
            File file = new File("./src/sample/txt", "database.txt");
            if (!file.exists( ))
            	file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(res);
            bw.close();
        }
    }

    private void changeScene(String fxml_file) {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(loadFXML(fxml_file), 900, 600);
        stage.setScene(scene);
    }

}
