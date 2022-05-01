package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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
    protected void tryLogin(ActionEvent event) {
        try{
        PreparedStatement stmt = DB.conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        stmt.setString(1,username.getText().toString());
        stmt.setString(2,password.getText().toString());
        ResultSet res = stmt.executeQuery();
        if(res.next()){
            notif.setText("Login Başarılı");
            System.out.println(Hydra.class.getResource("Dashboard.fxml"));
            FXMLLoader dashboardfxml = new FXMLLoader(Hydra.class.getResource("Dashboard.fxml"));
            Scene dashboardscene = new Scene(dashboardfxml.load());
            System.out.println(dashboardscene);

            DashboardController dbcont = dashboardfxml.getController();
            dbcont.setUsername(username.getText().toString());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            System.out.println(stage);
            stage.setScene(dashboardscene);
            stage.show();


        }else {
            notif.setText("Hatalı Login Bilgileri");
        }

        } catch (
                SQLException | IOException e) {
        System.out.println(e.getMessage());
    }
    }
}