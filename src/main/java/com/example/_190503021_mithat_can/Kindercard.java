package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        System.out.println("1");
        Image image = new Image(getClass().getResourceAsStream("img.jpeg"));

        if(kinder.get(3) != null){
            image = new Image(kinder.get(3));
        }
        System.out.println("2");

        icon.setImage(image);
        name.setText(kinder.get(0));
        desc.setText(kinder.get(1));
        id=Integer.parseInt(kinder.get(2));
    }
    public void del() throws IOException {
        DB.deletekinder(id);
        Scene scene = this.name.getScene();
        Node node = scene.lookup("list");
        VBox v = (VBox) scene.lookup("#list");
        AnchorPane a = (AnchorPane) this.name.getParent();
        v.getChildren().remove(a);
        System.out.println(v.getChildren());
        //DashboardController.refreshkinder();
    }
    public void click(){

    }
}
