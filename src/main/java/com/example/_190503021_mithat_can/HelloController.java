package com.example._190503021_mithat_can;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text notif;
    @FXML
    protected void tryLogin() {
        try{
        PreparedStatement stmt = Hydra.conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        stmt.setString(1,username.getText().toString());
        stmt.setString(2,password.getText().toString());
        ResultSet res = stmt.executeQuery();
        if(res.next()){
            notif.setText("Login Başarılı");
        }else {
            notif.setText("Hatalı Login Bilgileri");
        }

        } catch (
    SQLException e) {
        System.out.println(e.getMessage());
    }
    }
}