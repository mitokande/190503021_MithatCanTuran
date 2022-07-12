package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Eltern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupDetailsEltern implements Initializable {

    @FXML
    public TextField vorname;
    public TextField nachname;
    public TextField burgerId;
    public TextField telefonnummer;
    public TextField email;
    public TextField adresse;
    public int elternId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void update() throws IOException {
        Eltern eltern = new Eltern(vorname.getText(),nachname.getText(),burgerId.getText(),elternId,telefonnummer.getText(),email.getText(),adresse.getText());
        DB.saveEltern(eltern);
        Stage stage = (Stage) vorname.getScene().getWindow();
        stage.close();
        dash.refreshEltern();
    }
    public void setData(Eltern eltern) {
        elternId = eltern.getElternId();
        vorname.setText(eltern.getVorname());
        nachname.setText(eltern.getNachname());
        burgerId.setText(eltern.getBurgerId());
        telefonnummer.setText(eltern.getTelefonnummer());
        email.setText(eltern.getEmail());
        adresse.setText(eltern.getAdresse());
    }
}
