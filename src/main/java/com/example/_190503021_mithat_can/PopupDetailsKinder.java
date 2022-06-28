package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class PopupDetailsKinder implements Initializable{
    public String imagepath;

    public Kinder _kinder;
    @FXML
    public VBox vb;
    @FXML
    public TextField vorname;
    @FXML
    public TextField nachname;
    @FXML
    public TextField alter;
    @FXML
    public ChoiceBox<KeyValuePair> klasse = new ChoiceBox<KeyValuePair>();;
    @FXML
    public ChoiceBox eltern;
    @FXML
    public ImageView ss;
    @FXML
    public TextField burgerId;

    public void setData(Kinder kinder){
        _kinder = kinder;
        vorname.setText(kinder.getVorname());
        nachname.setText(kinder.getNachname());
        alter.setText(Integer.toString(kinder.getKind_alter()));
        klasse.setValue(new KeyValuePair(Integer.toString(kinder.getKlasse()),kinder.getKlasseAsObj().toString()));
        eltern.setValue(kinder.getEltern());
        burgerId.setText(kinder.getBurgerId());
        ss.setImage(new Image(kinder.getImage()));
        imagepath = kinder.getImage();

    }

    public void populateAreas(){
        ArrayList<Eltern> elterns = new ArrayList<>();
        elterns = DB.geteltern();
        for(Eltern e : elterns){
            String elternitem = e.getVorname() + e.getNachname();
            eltern.getItems().add(e);
        }
        ArrayList<Klasse> klasses = new ArrayList<>();
        klasses = DB.getClassrooms();
        for (Klasse k : klasses){
            klasse.getItems().add(new KeyValuePair(Integer.toString(k.getKlasseId()),k.toString()));

            System.out.println(k.getKlasseId());
        }
    }
    public  void update(ActionEvent event) throws IOException {
        Eltern e = (Eltern) eltern.getSelectionModel().getSelectedItem();
        System.out.println(Integer.parseInt(klasse.getValue().getKey()));
        Kinder kinder = new Kinder(vorname.getText(),nachname.getText(),burgerId.getText(),e,Integer.parseInt(klasse.getValue().getKey()));
        kinder.setKinderId(_kinder.getKinderId());
        kinder.setImage(imagepath);
        kinder.setKind_alter(Integer.parseInt(alter.getText()));
        System.out.println(Integer.parseInt(klasse.getValue().getKey()));
        DB.saveKinder(kinder);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        dash.refreshKinder();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAreas();
    }
    public void addimage(){
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);

                ss.setImage(new Image(file.getAbsolutePath().toString()));
                File theDir = new File(System.getProperty("user.home")+"/KinderGarten/");
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
                ImageIO.write(bufferedImage, "png",new File(System.getProperty("user.home")+"/KinderGarten/"+file.getName().toString()));
                imagepath = System.getProperty("user.home")+"/KinderGarten/"+file.getName().toString();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
