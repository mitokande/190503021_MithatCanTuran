package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupDetailsKlasse implements Initializable{
    public Klasse _klasse;
    @FXML
    public ChoiceBox kinderbox;
    @FXML
    public TextField klassename;
    @FXML
    public TextField klassegrosse;
    @FXML
    public ChoiceBox lehrerbox;
    @FXML
    public ListView kinderlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAreas();
    }
    public void populateAreas(){
        ArrayList<Kinder> kinders = DB.getkinder();
        for(Kinder k : kinders){
            kinderbox.getItems().add(k);
        }
        ArrayList<Lehrer> lehrers = DB.getLehrer();
        for(Lehrer l : lehrers){
            lehrerbox.getItems().add(l);
        }

    }
    public void setData(Klasse k){
        this._klasse = k;
        klassename.setText(k.getKlasseName());
        lehrerbox.setValue(DB.getLehrerSingle(k.getLehrerId()));
        klassegrosse.setText(Integer.toString(k.getKlassenGrosse()));
        ArrayList<Kinder> kinderArrayList = DB.getKinderVomKlasse(k);
        for(Kinder _k : kinderArrayList){
            kinderlist.getItems().add(_k);
        }
    }
    public void kinderAdd(){
        Kinder k =(Kinder) kinderbox.getSelectionModel().getSelectedItem();
        kinderlist.getItems().add(k);
    }
    public void deleteKinder(){
        Kinder k = (Kinder)kinderlist.getSelectionModel().getSelectedItem();
        //DB.deleteKinderVomAktivitat(k.getKinderId(),this.aktivitat.getAktivitatId());
        DB.deleteKinderVomKlasse(k.getKinderId());
        kinderlist.getItems().remove(k);
    }
    public void aktualisieren(ActionEvent event) throws IOException {
        Lehrer l = (Lehrer) lehrerbox.getSelectionModel().getSelectedItem();
        Klasse k = new Klasse(l.getLehrerId(),Integer.parseInt(klassegrosse.getText()));
        k.setKlasseName(klassename.getText());
        k.setKlasseId(_klasse.getKlasseId());
        DB.saveKlasse(k);
        List<Kinder> kinderList = kinderlist.getItems();
        for(Kinder _k : kinderList){
            DB.addKinderZumKlasse(_k.getKinderId(),k.getKlasseId());
        }
        dash.refreshClassroom();
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
