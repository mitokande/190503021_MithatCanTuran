package com.example._190503021_mithat_can;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class DashboardController  {

    @FXML
    public Text welcome;


    public void setUsername(String name){
        welcome.setText("Hoşgeldin: "+name);
    }
}
