package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupDetailsEltern implements Initializable {
    public static PopupDetailsEltern popupDetailsEltern ;
    @FXML
    public TextField vorname;
    public TextField nachname;
    public TextField burgerId;
    public TextField telefonnummer;
    public TextField email;
    public TextField adresse;
    public ScrollPane scrollbarOfZahlung;
    public VBox vboxZahlung;
    public int elternId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popupDetailsEltern = this;
    }

    public void populateZahlungen(){
        vboxZahlung.getChildren().clear();
        ArrayList<Zahlung> zahlungArrayList = DB.getZahlungenVomEltern(elternId);
        zahlungArrayList.forEach(element->{
            ArrayList<Bezahlung> bezahlungArrayList =  DB.getBezahlungVomZahlung(element.getZahlungId());
            Button btn = new Button();
            btn.setId(Integer.toString(element.getZahlungId()));
            btn.setOnAction(event -> {
                zahlungPopup(btn.getId());
            });
            btn.setText("Zahlung für:"+DB.getKinderSingle(element.getKinderId()).getVorname());
            vboxZahlung.getChildren().add(btn);
            btn.setMinWidth(vboxZahlung.getWidth());

            bezahlungArrayList.forEach(x->{
                Text textB = new Text();
                textB.setText("Ödeme Miktarı: " + x.getBetrag() + " | "+x.getDatum().toString());
                vboxZahlung.getChildren().add(textB);
            });
        });
    }

    private void zahlungPopup(String id) {
        try {
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-zahlung.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsZahlung popupDetailsZahlung = detailspopup.getController();
            popupDetailsZahlung.setData(DB.getZahlungSingle(Integer.parseInt(id)));

            stage.setTitle("Zahlung Details");
            stage.setScene(scene);
            stage.show();
        }catch (IOException o){
            System.out.println(o.getMessage());
        }
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
        populateZahlungen();

    }
}
