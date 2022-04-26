package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    public Text welcome;
    @FXML
    public TextArea list;

    public void setUsername(String name){
        welcome.setText("Hoşgeldin: "+name);
    }
    public void add(){
        DB.adddummyuser();
        refreshusers();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshusers();
    }
    public void refreshusers(){
        list.setText("");
        ArrayList<String> liste = DB.getusers();
        for(String l : liste){
            list.appendText(l+"\n");
        }
    }
}
