package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    public static DashboardController dash;
    @FXML
    public Text welcome;

    @FXML
    public VBox list;

    public void setUsername(String name){
        welcome.setText("Hoşgeldin: "+name);
        welcome.getStyleClass().add("text-warning");
    }
    public void add(){
        DB.adddummyuser();
        //refreshkinder();
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

        ArrayList<ArrayList<String>> liste = DB.getkinder();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader kindercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(kindercard.load());
            Kindercard kindercardController = kindercard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);
        System.out.println(liste);
    }
    public void refreshClassroom() throws IOException{
        list.getChildren().clear();

    }
    public void refreshViewList(int index) throws IOException{
        switch (index){
            case 0:
                refreshKinder();
                break;
            case 1:
                refreshKinder();
                break;
            case 2:
                refreshClassroom();
                break;
            case 3:
                refreshKinder();
                break;
        }
    }
    public void newkinder() throws IOException {
        FXMLLoader kinderpopup = new FXMLLoader(Hydra.class.getResource("popup-add-kinder.fxml"));
        Scene scene = new Scene(kinderpopup.load());
        Stage stage = new Stage();
        stage.setTitle("Add new kinder");
        stage.setScene(scene);
        stage.show();
    }

    public void Navigation(MouseEvent event){
        AnchorPane navObj= (AnchorPane) event.getSource();
        String navId = navObj.getId();
        try {
            switch (navId){
                case "studentNAV":
                    System.out.println("Student Penceresini aç");
                    refreshViewList(0);
                    break;
                case "teacherNAV":
                    System.out.println("Teacher Penceresini aç");
                    refreshViewList(1);
                    break;
                case "classroomNAV":
                    System.out.println("Classroom Penceresini aç");
                    refreshViewList(2);
                    break;
                case "settingNAV":
                    System.out.println("Setting Penceresini aç");
                    refreshViewList(3);
                    break;
            }
        }catch (IOException e){

        }

    }

}
