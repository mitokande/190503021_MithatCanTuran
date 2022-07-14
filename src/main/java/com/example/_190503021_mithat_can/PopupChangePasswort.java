package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Lehrer;
import com.example._190503021_mithat_can.BaseClass.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupChangePasswort implements Initializable {

    public Settings mySettings;

    @FXML
    public TextField oldpass;
    @FXML
    public TextField newpass;
    @FXML
    public TextField newpassConfirmation;
    @FXML
    public Text prompt;
    public User myuser;
    public void savePass(){
        prompt.setVisible(true);
        Lehrer l = DB.getLehrerSingle(myuser.LehrerId);
        if(oldpass.getText().equals(l.getPasswort())){
            if(newpass.getText().equals(newpassConfirmation.getText())){
                mySettings.getPass(newpass.getText());
                Stage stage = (Stage) newpass.getScene().getWindow();
                stage.close();
            }
            else{
                prompt.setText("Neue passwörter ist nicht gleich.");
            }
        }else{
            prompt.setText("Alte Passwort is falsch.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prompt.setVisible(false);
    }
}
