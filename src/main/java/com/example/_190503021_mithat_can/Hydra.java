package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.Klasse;
import com.example._190503021_mithat_can.BaseClass.Lehrer;
import com.example._190503021_mithat_can.BaseClass.SystemMethoden;
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
        connect();
        clastest();
    }
    public void clastest(){
        SystemMethoden Settings = new SystemMethoden();
        Klasse k = new Klasse(new Lehrer("mithat","turan","123123","mito","12312","a@g.com"),12);
        Settings.KlasseErstellen(k);
        Settings.ListKlasse();
    }
    public  static Connection conn = null;

    public static void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\Public\\YazılımMuh\\sqlite\\sqlite-tools\\kindergarten.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM users");
            if(res.next()){
                System.out.println("username: "+res.getString("username")+" Pass: "+res.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch();
    }
}