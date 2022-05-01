package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Eltern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class popup_add_kinder implements Initializable {

    @FXML
    public VBox vb;
    @FXML
    public TextField vorname;
    @FXML
    public TextField nachname;
    @FXML
    public TextField alter;
    @FXML
    public ChoiceBox klasse;
    @FXML
    public ChoiceBox eltern;
    @FXML
    public ImageView ss;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateareas();
    }
    public void addkind() throws SQLException {

    }
    public void populateareas(){
        ArrayList<Eltern> elterns = new ArrayList<>();
        elterns = DB.geteltern();
        for(Eltern e : elterns){
            String elternitem = e.getVorname() + e.getNachname();
            eltern.getItems().add(e);

        }
    }
    public void addimage(){
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);

                ss.setImage(new Image(file.getAbsolutePath().toString()));
                File theDir = new File(System.getProperty("user.home")+"/KinderGarten/");
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
                ImageIO.write(bufferedImage, "png",new File(System.getProperty("user.home")+"/KinderGarten/"+file.getName().toString()));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
