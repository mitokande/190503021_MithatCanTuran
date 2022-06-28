package com.example._190503021_mithat_can;
import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Eltern;
import com.example._190503021_mithat_can.BaseClass.Lehrer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupAddEltern {

    @FXML
    public TextField vorname;
    @FXML
    public TextField nachname;
    @FXML
    public TextField burgerId;
    @FXML
    public TextField telefonnummer;
    @FXML
    public TextField adresse;
    @FXML
    public TextField email;


    public void addEltern(ActionEvent event) throws IOException{
        Eltern eltern = new Eltern(vorname.getText(),nachname.getText(),burgerId.getText(),0,telefonnummer.getText(),email.getText(),adresse.getText());
        DB.addEltern(eltern);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        dash.refreshTeacher();
    }

}
