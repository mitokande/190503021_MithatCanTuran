package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Set;

public class Hydra extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Hydra.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hydra Kindergarten!");
        stage.setScene(scene);
        stage.show();
        DB.connect();
        //clastest();
    }
    public void clastest(){
        SystemMethoden Settings = new SystemMethoden();
        Klasse k = new Klasse(new Lehrer("mithat","turan","123123","mito","12312","a@g.com"),12);
        Settings.ListKlasse();
        Eltern e = new Eltern("Ali","Veli","1112223332");
        Settings.KlasseErstellen(k);
        Settings.ElternErstellen(e);
    }

    public static void main(String[] args) {
        launch();
    }
}