package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Kindercard {
    public String type = "student";
    public int id;
    @FXML
    public Label name;
    @FXML
    public Label desc;
    @FXML
    public ImageView icon;

    public void setdata(Kinder kinder){
        type = "kinder";
        Image image = new Image(getClass().getResourceAsStream("img.jpeg"));

        if(kinder.getImage() != null){
            image = new Image(kinder.getImage());
        }

        icon.setImage(image);
        name.setText(kinder.getVorname()+" "+ kinder.getNachname());
        desc.setText("Alter:" + Integer.toString(kinder.getKind_alter())+ " Eltern:"+ kinder.getEltern().getVorname()+ " " + kinder.getEltern().getNachname());
        id=kinder.getKinderId();
    }
    public void del() throws IOException {
        if(type == "kinder"){
            DB.deleteKinder(id);
        }else if(type == "klasse"){
            DB.deleteKlasse(id);
        }else if(type == "lehrer"){
            DB.deleteLehrer(id);
        }else if(type == "aktivitat"){
            DB.deleteAktivitat(id);
        }else if(type == "eltern"){
            DB.deleteEltern(id);
        }
        Scene scene = this.name.getScene();
        Node node = scene.lookup("list");
        VBox v = (VBox) scene.lookup("#list");
        AnchorPane a = (AnchorPane) this.name.getParent();
        v.getChildren().remove(a);
        System.out.println(v.getChildren());
    }
    public void setdata(Klasse klasse){
        ArrayList<Lehrer> lehrerArrayList = DB.getLehrer();
        Lehrer lehrer = new Lehrer(0,"","","","","","");
        for(Lehrer l : lehrerArrayList){
            if(l.getLehrerId() == klasse.getLehrerId()){
                lehrer = l;
            }
        }
        type = "klasse";
        Image image = new Image(getClass().getResourceAsStream("board.png"));
        icon.setImage(image);
        name.setText(klasse.getKlasseName());
        desc.setText("Klassen Große:" + Integer.toString(klasse.getKlassenGrosse()) + " Lehrer:"+lehrer.getVorname() + " " + lehrer.getNachname() );
        id=klasse.getKlasseId();
    }
    public void setdata(Aktivitat aktivitat){
        ArrayList<Aktivitat> lehrerArrayList = DB.getAktivitat();

        type = "aktivitat";
        Image image = new Image(getClass().getResourceAsStream("kite.png"));
        icon.setImage(image);
        name.setText(aktivitat.getAktivitatName());
        desc.setText("Aktivitat Große:" + Integer.toString(aktivitat.getAktivitatGrosse()) + " Datum:"+ aktivitat.getAktivitatdatum().toString());
        id=aktivitat.getAktivitatId();
    }
    public void setdata(Lehrer lehrer){
        type = "lehrer";
        Image image = new Image(getClass().getResourceAsStream("teacher.png"));
        icon.setImage(image);
        name.setText(lehrer.getVorname() + " " + lehrer.getNachname());
        desc.setText("Sınıfları "+ lehrer.getKlasse().toString());
        id=lehrer.getLehrerId();
    }
    public void edit() throws IOException{
        if(type=="aktivitat"){
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-aktivitat.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsAktivitat popupDetailsAktivitat = detailspopup.getController();
            Aktivitat aktivitat = DB.getAktivitatSingle(id);
            popupDetailsAktivitat.setData(aktivitat);
            stage.setTitle("Aktivitat Details");
            stage.setScene(scene);
            stage.show();
        }
        if(type=="kinder"){
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-kinder.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsKinder popupDetailsKinder = detailspopup.getController();
            Kinder kinder = DB.getKinderSingle(id);
            popupDetailsKinder.setData(kinder);
            stage.setTitle("Aktivitat Details");
            stage.setScene(scene);
            stage.show();
        }
        if(type=="klasse"){
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-klasse.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsKlasse popupDetailsKlasse = detailspopup.getController();
            Klasse klasse = DB.getKlasseSingle(id);
            popupDetailsKlasse.setData(klasse);
            stage.setTitle("Klasse Details");
            stage.setScene(scene);
            stage.show();
        }
        if(type=="lehrer"){
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-lehrer.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsLehrer popupDetailsLehrer = detailspopup.getController();
            Lehrer lehrer= DB.getLehrerSingle(id);
            popupDetailsLehrer.setData(lehrer);
            stage.setTitle("Lehrer Details");
            stage.setScene(scene);
            stage.show();
        }
        if(type=="eltern"){
            FXMLLoader detailspopup = new FXMLLoader(Hydra.class.getResource("popup-details-eltern.fxml"));
            Scene scene = new Scene(detailspopup.load());
            Stage stage = new Stage();
            PopupDetailsEltern popupDetailsEltern = detailspopup.getController();
            Eltern eltern = DB.getElternSingle(id);
            popupDetailsEltern.setData(eltern);
            stage.setTitle("Eltern Details");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setdata(Eltern eltern) {
        type = "eltern";
        Image image = new Image(getClass().getResourceAsStream("teacher.png"));
        icon.setImage(image);
        name.setText(eltern.getVorname() + " " + eltern.getNachname());
        ArrayList<Kinder> kinders = DB.getkinder();
        String s ="Çocukları: ";
        for(Kinder k : kinders){
            if(k.getEltern().getElternId()==eltern.getElternId()){
                s += k.getVorname() + " " + k.getNachname()+" | ";
            }
        }
        desc.setText(s);

        id=eltern.getElternId();
    }
}
