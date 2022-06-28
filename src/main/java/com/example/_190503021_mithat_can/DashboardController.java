package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    public String currentView = "student";
    public static DashboardController dash;
    @FXML
    public Text welcome;

    @FXML
    public VBox list;
    @FXML
    public Text title;
    public void setUsername(String name){
        welcome.setText("Hoşgeldin: "+name);
        welcome.getStyleClass().add("text-warning");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dash = this;
            refreshKinder();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void refreshKinder() throws IOException {
        //list.setText("");
        list.getChildren().clear();

        ArrayList<Kinder> liste = DB.getkinder();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader kindercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(kindercard.load());
            Kindercard kindercardController = kindercard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);

    }
    public void refreshClassroom() throws IOException{
        list.getChildren().clear();
        ArrayList<Klasse> liste = DB.getClassrooms();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader klassecard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(klassecard.load());
            Kindercard kindercardController = klassecard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);
    }
    public void refreshAktivitat() throws IOException{
        list.getChildren().clear();
        ArrayList<Aktivitat> liste = DB.getAktivitat();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader aktivitatcard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(aktivitatcard.load());
            Kindercard kindercardController = aktivitatcard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);
    }
    public void refreshTeacher() throws IOException{
        list.getChildren().clear();
        ArrayList<Lehrer> liste = DB.getLehrer();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader Lehrercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(Lehrercard.load());
            Kindercard kindercardController = Lehrercard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);
    }
    public void openSettings() throws IOException{
        list.getChildren().clear();
        FXMLLoader Lehrercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
        list.getChildren().add(Lehrercard.load());

    }
    public void refreshViewList(int index) throws IOException{
        switch (index){
            case 0:
                refreshKinder();
                title.setText("Kinder");
                break;
            case 1:
                title.setText("Lehrer");
                refreshTeacher();
                break;
            case 2:
                title.setText("Klassen");
                refreshClassroom();
                break;
            case 3:
                title.setText("Aktivität");
                refreshAktivitat();
                break;
            case 4:
                openSettings();
                break;
        }
    }
    public void popup() throws IOException {
        Scene scene;
        Stage stage;
        switch (currentView){
            case "student":
                FXMLLoader kinderpopup = new FXMLLoader(Hydra.class.getResource("popup-add-kinder.fxml"));
                scene = new Scene(kinderpopup.load());
                stage = new Stage();
                stage.setTitle("Add new Child");
                stage.setScene(scene);
                stage.show();
                break;
            case "teacher":
                FXMLLoader lehrerpopup = new FXMLLoader(Hydra.class.getResource("popup-add-lehrer.fxml"));
                scene = new Scene(lehrerpopup.load());
                stage = new Stage();
                stage.setTitle("Add new Teacher");
                stage.setScene(scene);
                stage.show();
                break;
            case "classroom":
                FXMLLoader classroompopup = new FXMLLoader(Hydra.class.getResource("popup-add-klasse.fxml"));
                scene = new Scene(classroompopup.load());
                stage = new Stage();
                stage.setTitle("Add new Classroom");
                stage.setScene(scene);
                stage.show();
                break;
            case "aktivitat":
                FXMLLoader aktivitatpopup = new FXMLLoader(Hydra.class.getResource("popup-add-aktivitat.fxml"));
                scene = new Scene(aktivitatpopup.load());
                stage = new Stage();
                stage.setTitle("Add new Classroom");
                stage.setScene(scene);
                stage.show();
                break;
        }
    }

    public void Navigation(MouseEvent event){
        AnchorPane navObj= (AnchorPane) event.getSource();
        String navId = navObj.getId();
        try {
            if(navId!=null){
                switch (navId){
                    case "studentNAV":
                        currentView = "student";
                        refreshViewList(0);
                        break;
                    case "teacherNAV":
                        currentView = "teacher";
                        refreshViewList(1);
                        break;
                    case "classroomNAV":
                        currentView = "classroom";
                        refreshViewList(2);
                        break;
                    case "aktivitatNAV":
                        currentView = "aktivitat";
                        refreshViewList(3);
                        break;
                    case "settingNAV":
                        currentView = "setting";
                        refreshViewList(4);
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException e){

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

}
