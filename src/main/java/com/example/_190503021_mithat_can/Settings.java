package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Settings implements Initializable {

    @FXML
    public TextField benutzername;
    @FXML
    public TextField email;

    public User myuser;
    public String pass;

    public void setData(){
        myuser = HelloController.user;
        Lehrer l = DB.getLehrerSingle(myuser.LehrerId);
        benutzername.setText(l.getBenutzername());
        pass = l.getPasswort();
        email.setText(l.getEmail());
    }
    public void changePass() throws IOException {
        FXMLLoader passwortpopup = new FXMLLoader(Hydra.class.getResource("popup-change-passwort.fxml"));
        Scene scene = new Scene(passwortpopup.load());
        PopupChangePasswort popupChangePasswort = passwortpopup.getController();
        popupChangePasswort.myuser = myuser;
        popupChangePasswort.mySettings = this;
        Stage stage = new Stage();
        stage.setTitle("Passwort Ändern");
        stage.setScene(scene);
        stage.show();
    }
    public void getPass(String newPass){
        pass = newPass;
    }
    public void SaveData(){
        Lehrer l = DB.getLehrerSingle(myuser.LehrerId);
        l.setPasswort(pass);
        l.setBenutzername(benutzername.getText());
        l.setEmail(email.getText());
        DB.saveLehrer(l);
        TilePane tilePane = new TilePane();
        Scene scene = new Scene(tilePane,145,30);
        Button btn = new Button();
        tilePane.setMinSize(100,30);
        btn.setMinSize(100,30);
        btn.setText("Anderungen Aktualisiert");

        tilePane.getChildren().add(btn);
        Stage stage = new Stage();
        stage.setTitle("Passwort Ändern");
        EventHandler<ActionEvent> actionEventHandler =
                et -> {
                    stage.close();
                };
        btn.setOnAction(actionEventHandler);

        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
