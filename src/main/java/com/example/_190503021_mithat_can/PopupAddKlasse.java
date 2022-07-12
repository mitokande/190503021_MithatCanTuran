package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;
public class PopupAddKlasse implements Initializable{

    @FXML
    public TextField klassen_name;
    @FXML
    public TextField klassengrosse;
    @FXML
    public ChoiceBox lehrer;

    public void addClassroom(ActionEvent event) throws IOException{
        //Lehrer lehrer = new Lehrer(0,vorname.getText(),nachname.getText(),burgerId.getText(),benutzername.getText(),passwort.getText(),email.getText());
        Lehrer l = (Lehrer) lehrer.getSelectionModel().getSelectedItem();
        Klasse klasse = new Klasse(l.getLehrerId(),Integer.parseInt(klassengrosse.getText()));
        klasse.setKlasseName(klassen_name.getText());
        DB.AddClassroom(klasse);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        dash.refreshClassroom();
        dash.populateFilter();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAreas();
    }

    public void populateAreas(){
        ArrayList<Lehrer> lehrers = new ArrayList<>();
        lehrers = DB.getLehrer();
        for(Lehrer l : lehrers){
            lehrer.getItems().add(l);
        }
    }
}
