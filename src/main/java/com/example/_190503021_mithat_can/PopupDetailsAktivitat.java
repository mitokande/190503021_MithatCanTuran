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

public class PopupDetailsAktivitat implements Initializable {
    public Aktivitat aktivitat;
    @FXML
    public ChoiceBox kinderbox;
    @FXML
    public TextField aktivitatname;
    @FXML
    public TextField aktivitatgrosse;
    @FXML
    public DatePicker aktivitatdatum;
    @FXML
    public ListView kinderlist;
    public void populateAreas(){
        ArrayList<Kinder> kinders = DB.getkinder();
        for(Kinder k : kinders){
            kinderbox.getItems().add(k);
        }

    }
    public void setData(Aktivitat aktivitat){
        this.aktivitat = aktivitat;
        aktivitatname.setText(aktivitat.getAktivitatName());
        aktivitatdatum.setValue(aktivitat.getAktivitatdatum());
        aktivitatgrosse.setText(Integer.toString(aktivitat.getAktivitatGrosse()));
        ArrayList<Kinder> kinderArrayList = DB.getKinderVomAktivitat(aktivitat);
        for(Kinder k : kinderArrayList){
            kinderlist.getItems().add(k);
        }
    }
    public void kinderAdd(){
        Kinder k =(Kinder) kinderbox.getSelectionModel().getSelectedItem();
        kinderlist.getItems().add(k);
    }
    public void deleteKinder(){
        Kinder k = (Kinder)kinderlist.getSelectionModel().getSelectedItem();
        DB.deleteKinderVomAktivitat(k.getKinderId(),this.aktivitat.getAktivitatId());
        kinderlist.getItems().remove(k);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAreas();
    }
    public void aktualisieren(ActionEvent event) throws IOException {
        Aktivitat a = new Aktivitat(this.aktivitat.getAktivitatId(),Integer.parseInt(aktivitatgrosse.getText()),aktivitatdatum.getValue());
        a.setAktivitatName(aktivitatname.getText());
        DB.saveAktivitat(a);
        List<Kinder> kinderList = kinderlist.getItems();
        for(Kinder k : kinderList){
            Aktivitat_Kinder aktivitat_kinder = new Aktivitat_Kinder(0,a.getAktivitatId(),k.getKinderId());
            DB.addKinderZumAktivitat(aktivitat_kinder);
        }
        dash.refreshAktivitat();
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
