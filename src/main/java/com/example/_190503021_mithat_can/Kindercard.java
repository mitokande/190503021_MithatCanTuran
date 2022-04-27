package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.*;

import java.io.IOException;
import java.util.ArrayList;

public class Kindercard {
    public int id;
    @FXML
    public Label name;
    @FXML
    public Label desc;
    @FXML
    public ImageView icon;

    public void setdata(ArrayList<String> kinder){
//        Image image = new Image(Hydra.class.getResourceAsStream("src/main/resources/img.jpeg"));
//        icon.setImage(image);
        name.setText(kinder.get(0));
        desc.setText(kinder.get(1));
        id=Integer.parseInt(kinder.get(2));
    }
    public void del() throws IOException {
        //DB.deletekinder(id);
        Scene scene = this.name.getScene();
        Node node = scene.lookup("list");
        VBox v = (VBox) scene.lookup("#list");
        v.getChildren().remove(0);
        System.out.println(v.getChildren());
        //DashboardController.refreshkinder();
    }
    public void click(){

    }
}
