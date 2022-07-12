package com.example._190503021_mithat_can;
import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupAddAktivitat implements Initializable{

    @FXML
    public TextField aktivitatname;
    @FXML
    public TextField aktivitatgrosse;
    @FXML
    public DatePicker aktivitatdatum;



    public void addAktivitat(ActionEvent event) throws IOException {
        LocalDate date = aktivitatdatum.getValue();
        Aktivitat aktivitat = new Aktivitat(0,Integer.parseInt(aktivitatgrosse.getText()),date);
        aktivitat.setAktivitatName(aktivitatname.getText());
        DB.AddAktivitat(aktivitat);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        dash.refreshAktivitat();
        dash.populateFilter();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
