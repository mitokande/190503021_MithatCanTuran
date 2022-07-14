package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.Bezahlung;
import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Zahlung;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PopupDetailsRechnung implements Initializable {

    public String path = System.getProperty("user.home")+"/Desktop/invoide.pdf";

    @FXML
    public VBox vboxZahlungen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateZahlungen();
    }

    public void populateZahlungen(){
        ArrayList<Zahlung> zahlungArrayList = DB.getZahlungen();
        for (Zahlung z : zahlungArrayList){
            CheckBox ch = new CheckBox();
            ch.setId(Integer.toString(z.getZahlungId()));
            ch.setText("Zahlung für: " +
                    DB.getElternSingle(z.getElternId()) +
                    " Kinder: " + DB.getKinderSingle(z.getKinderId()).getVorname() + " "+
                    DB.getKinderSingle(z.getKinderId()).getNachname()+  " Gleichgewicht:"+
                    z.getGezahlterBetrag()+"/"+z.getGesamtesumme());
            vboxZahlungen.getChildren().add(ch);
        }
    }


    public void createRechnung() throws IOException {
        ArrayList<Zahlung> zahlungs = new ArrayList<>();
        vboxZahlungen.getChildren().forEach(x->{
            CheckBox ch = (CheckBox)x;
            if(ch.isSelected()){
                zahlungs.add(DB.getZahlungSingle(Integer.parseInt(ch.getId())));
            }
        });


        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDFont font = PDType0Font.load(doc, new File("C:\\Users\\Public\\Projeler\\YazılımMuh\\src\\main\\resources\\com\\example\\_190503021_mithat_can\\AlbertSans-Black.ttf"));
        //PDFont font = new PDType1Font(PDType1Font.HELVETICA_BOLD.getCOSObject());
        try (PDPageContentStream contents = new PDPageContentStream(doc, page))
        {

            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.setLeading(14.5f);

            String message = "";

            for(Zahlung zahlung : zahlungs){
                ArrayList<Bezahlung> bezahlungArrayList = DB.getBezahlungVomZahlung(zahlung.getZahlungId());
                contents.showText("Eltern: "+DB.getElternSingle(zahlung.getElternId()).getVorname()+
                                DB.getElternSingle(zahlung.getElternId()).getNachname()+
                        " Kinder: "+ DB.getKinderSingle(zahlung.getKinderId()).getVorname()+ " "+
                        DB.getKinderSingle(zahlung.getKinderId()).getNachname() + " için Fatura Bilgileri");
                contents.newLine();

                for(Bezahlung b : bezahlungArrayList){
                    contents.showText("    Ödeme Miktarı: " + b.getBetrag() + " Ödeme Tarihi: " + b.getDatum().toString());
                    contents.newLine();

                }
            }


            contents.endText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        doc.save(path);
    }
}
