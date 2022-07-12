package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.Bezahlung;
import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Zahlung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;
import static com.example._190503021_mithat_can.PopupDetailsEltern.popupDetailsEltern;

public class PopupDetailsZahlung implements Initializable {

    private Zahlung myZahlung;
    private int localpreis;
    @FXML
    public Label zahlungId;
    @FXML
    public Label zahler;
    @FXML
    public Label personal;
    @FXML
    public Label gesamtepreis;
    @FXML
    public Label gezahlterpreis;
    @FXML
    public TextField bezahlung;
    private ArrayList<Bezahlung> bezahlungArrayList = new ArrayList<>();
    public void update() throws IOException {
        bezahlungArrayList.forEach(bezahlung1 -> {
            DB.AddBezahlung(bezahlung1);
        });
        Stage stage = (Stage) zahler.getScene().getWindow();
        stage.close();
        popupDetailsEltern.populateZahlungen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void bezahlen(){
        Bezahlung b = new Bezahlung(0,myZahlung.getZahlungId(),Integer.parseInt(bezahlung.getText()), LocalDate.now());
        bezahlungArrayList.add(b);
        localpreis+= b.getBetrag();
        gezahlterpreis.setText("Gezahlter Preis: "+localpreis);
    }
    public void setData(Zahlung zahlung){
        System.out.println(zahlung.getZahlungId());
        myZahlung = zahlung;
        myZahlung.setZahlungId(zahlung.getZahlungId());
        zahlungId.setText("Zahlung Id: "+myZahlung.getZahlungId());
        zahler.setText("Zahler: "+ DB.getElternSingle(myZahlung.getElternId()));
        personal.setText("Personal: "+DB.getLehrerSingle(HelloController.user.LehrerId));
        gesamtepreis.setText("Gesamte Preis: "+myZahlung.getGesamtesumme());
        localpreis = myZahlung.getGezahlterBetrag();
        gezahlterpreis.setText("Gezahlter Preis: "+localpreis);
    }
}
