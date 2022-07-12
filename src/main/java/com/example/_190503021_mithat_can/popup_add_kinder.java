package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import com.example._190503021_mithat_can.BaseClass.Eltern;
import com.example._190503021_mithat_can.BaseClass.Kinder;
import com.example._190503021_mithat_can.BaseClass.Klasse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example._190503021_mithat_can.DashboardController.dash;

public class popup_add_kinder implements Initializable {

    public static popup_add_kinder popupAddKinder;

    @FXML
    public VBox vb;
    @FXML
    public TextField vorname;
    @FXML
    public TextField nachname;
    @FXML
    public TextField alter;
    @FXML
    public ChoiceBox klasse;
    @FXML
    public ChoiceBox eltern;
    @FXML
    public ImageView ss;
    @FXML
    public TextField burgerId;



    public String imagepath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateareas();
        popupAddKinder = this;
    }
    public void addkind() throws  IOException {
        Eltern e = (Eltern) eltern.getSelectionModel().getSelectedItem();
        Klasse k = (Klasse) klasse.getSelectionModel().getSelectedItem();
        Kinder kinder = new Kinder(vorname.getText(),nachname.getText(),burgerId.getText(),e,k.getKlasseId());
        kinder.setImage(imagepath);
        kinder.setKind_alter(Integer.parseInt(alter.getText()));
        DB.addkinder(kinder);
        dash.refreshKinder(null);
        dash.populateFilter();
    }
    public void populateareas(){
        eltern.getItems().clear();
        klasse.getItems().clear();
        ArrayList<Eltern> elterns = new ArrayList<>();
        elterns = DB.geteltern();
        for(Eltern e : elterns){
            String elternitem = e.getVorname() + e.getNachname();
            eltern.getItems().add(e);
        }
        ArrayList<Klasse> klasses = new ArrayList<>();
        klasses = DB.getClassrooms();
        for (Klasse k : klasses){
            klasse.getItems().add(k);
        }
    }
    public void elternAdd() throws IOException {
        FXMLLoader elternpopup = new FXMLLoader(Hydra.class.getResource("popup-add-eltern.fxml"));
        Scene scene = new Scene(elternpopup.load());
        Stage stage = new Stage();
        stage.setTitle("Neue Eltern");
        stage.setScene(scene);
        stage.show();

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
