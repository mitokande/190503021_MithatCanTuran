package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    public Text welcome;

    @FXML
    public VBox list;

    public void setUsername(String name){
        welcome.setText("Hoşgeldin: "+name);
        welcome.getStyleClass().add("text-warning");
    }
    public void add(){
        DB.adddummyuser();
        //refreshkinder();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshkinder();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void refreshkinder() throws IOException {
        //list.setText("");
        ArrayList<ArrayList<String>> liste = DB.getkinder();
//        for(String l : liste){
//            list.appendText(l+"\n");
//        }

        for(ArrayList<String> s : liste){
            FXMLLoader kindercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(kindercard.load());
            Kindercard kindercardController = kindercard.getController();
            kindercardController.setdata(s);


        }
        list.setSpacing(12);
        System.out.println(liste);


    }
}
