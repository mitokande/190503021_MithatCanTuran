package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Lehrer;
import com.example._190503021_mithat_can.BaseClass.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupChangePasswort {

    public Settings mySettings;

    @FXML
    public TextField oldpass;
    @FXML
    public TextField newpass;
    @FXML
    public TextField newpassConfirmation;
    public User myuser;
    public void savePass(){
        Lehrer l = DB.getLehrerSingle(myuser.LehrerId);
        if(oldpass.getText().equals(l.getPasswort())){
            if(newpass.getText().equals(newpassConfirmation.getText())){
                mySettings.getPass(newpass.getText());
                Stage stage = (Stage) newpass.getScene().getWindow();
                stage.close();
            }
        }
    }
}
