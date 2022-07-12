package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {

    public static User user = new User();
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text notif;

    public void keylogin(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            try{
                PreparedStatement stmt = DB.conn.prepareStatement("SELECT * FROM lehrer WHERE benutzername=? AND passwort=?");
                stmt.setString(1,username.getText().toString());
                stmt.setString(2,password.getText().toString());
                ResultSet res = stmt.executeQuery();
                if(res.next()){
                    notif.setText("Login Başarılı");
                    System.out.println(Hydra.class.getResource("Dashboard.fxml"));
                    FXMLLoader dashboardfxml = new FXMLLoader(Hydra.class.getResource("Dashboard.fxml"));
                    Scene dashboardscene = new Scene(dashboardfxml.load());
                    System.out.println(dashboardscene);
                    user.username = username.getText();
                    user.password = password.getText();
                    user.LehrerId = res.getInt("lehrerId");
                    DashboardController dbcont = dashboardfxml.getController();
                    dbcont.setUsername(res.getString("vorname"));
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

    @FXML
    protected void tryLogin(ActionEvent event) {
        try{
            PreparedStatement stmt = DB.conn.prepareStatement("SELECT * FROM lehrer WHERE benutzername=? AND passwort=?");
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
                dbcont.setUsername(res.getString("vorname"));
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