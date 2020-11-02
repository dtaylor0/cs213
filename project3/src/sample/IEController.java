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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private void handleLoadFXML(ActionEvent event) throws IOException
    {
        Object eventSrc = event.getSource();
        File file = new File(Path.path);
        if (!file.exists( ))
            file.createNewFile();
        if (eventSrc.equals(exportBtn))
        {
            if (eventSrc.equals(exportBtn))
            {
                String filePath = file.getAbsolutePath();
                for(int i = 0; i < filePath.length() - 1; i++)
                {
                    if(filePath.charAt(i) == '\\' && filePath.charAt(i + 1) == '.')
                        filePath = filePath.substring(0, i) + filePath.substring(i + 2);
                }
                output.appendText("This is the file path to database.txt:\n" + filePath + "\n");
            }
        }
        else if (eventSrc.equals(importBtn))
        {
            String res = "";
            FileChooser fileChooser = new FileChooser();
            try
            {
                Stage stage = (Stage) bp.getScene().getWindow();
                try
                {
                    File file2 = fileChooser.showOpenDialog(stage);// needs to be try-catch for null pointer exception
                    Scanner sc = new Scanner(file2);
                    while(sc.hasNextLine())
                        res += sc.nextLine() + "\n";
                    sc.close();
                }
                catch(IOException | NullPointerException e)
                {
                    output.appendText("You didn't open a file.\n");
                    return;
                }
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
            }
            file = new File(Path.path);
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(res);
            bw.close();
            output.appendText("Database successfully imported.\n");
        }
    }


    @FXML
    private void goHome() {
        Stage stage = (Stage) bp.getScene().getWindow();
        Scene scene = new Scene(Objects.requireNonNull(loadFXML("home.fxml")), 900, 600);
        stage.setScene(scene);
    }
}
