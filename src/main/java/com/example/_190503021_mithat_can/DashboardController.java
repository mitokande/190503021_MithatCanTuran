package com.example._190503021_mithat_can;

import com.example._190503021_mithat_can.BaseClass.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DashboardController implements Initializable {
    public String currentView = "student";
    public static DashboardController dash;
    private boolean isElternView;
    public Text welcome;
    public Button popupbtn;
    public Button elternpopupbtn;
    public VBox list;
    public Text title;
    public ScrollPane scrollbarOfEltern;
    public ScrollPane scrollbarOfKlasse;
    public ScrollPane scrollbarOfLehrer;
    public TextField nameBar;
    public VBox vboxEltern;
    public VBox vboxKlasse;
    public VBox vboxLehrer;

    public void setUsername(String name){
        welcome.setText("Benutzer: "+name);
        welcome.getStyleClass().add("text-warning");
    }
    public void eltern() throws IOException {
        if(isElternView){
            refreshKinder(null);
            isElternView = false;
        }else{
            refreshEltern();
            isElternView=true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dash = this;
            refreshKinder(null);
            populateFilter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void refreshEltern() throws IOException {
        //list.setText("");
        list.getChildren().clear();

        ArrayList<Eltern> liste = DB.geteltern();

        for (int i = 0;i<liste.size();i++ ){
            FXMLLoader kindercard = new FXMLLoader(Hydra.class.getResource("kindercard.fxml"));
            list.getChildren().add(kindercard.load());
            Kindercard kindercardController = kindercard.getController();
            kindercardController.setdata(liste.get(i));
        }
        list.setSpacing(12);

    }
    public  void refreshKinder(Predicate<Kinder> kinderPredicate) throws IOException {
        //list.setText("");
        list.getChildren().clear();

        ArrayList<Kinder> liste = DB.getkinder();
        Collections.sort(liste, new Comparator<Kinder>() {
            @Override
            public int compare(Kinder o1, Kinder o2) {
                if(o1.getKinderId()>o2.getKinderId()){
                    return -1;
                }else if(o1.getKinderId()<o2.getKinderId()){
                    return 1;
                }
                return 0;
            }
        });


        if(kinderPredicate != null){
            liste = (ArrayList<Kinder>) liste.stream().filter(kinderPredicate).collect(Collectors.toList());
        }

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
        System.out.println("asd");
        list.getChildren().clear();
        FXMLLoader Lehrercard = new FXMLLoader(Hydra.class.getResource("settings.fxml"));
        System.out.println("asd23");
        list.getChildren().add(Lehrercard.load());
        System.out.println("asd");

    }
    public void refreshViewList(int index) throws IOException{
        switch (index){
            case 0:
                elternpopupbtn.setVisible(true);
                popupbtn.setVisible(true);
                refreshKinder(null);
                title.setText("Kinder");
                break;
            case 1:
                elternpopupbtn.setVisible(false);
                popupbtn.setVisible(true);
                title.setText("Lehrer");
                refreshTeacher();
                break;
            case 2:
                elternpopupbtn.setVisible(false);
                popupbtn.setVisible(true);
                title.setText("Klassen");
                refreshClassroom();
                break;
            case 3:
                elternpopupbtn.setVisible(false);
                popupbtn.setVisible(true);
                title.setText("Aktivität");
                refreshAktivitat();
                break;
            case 4:
                popupbtn.setVisible(false);
                elternpopupbtn.setVisible(false);
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
        popupbtn.setVisible(true);
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
                        if(!HelloController.user.isAdmin){
                            popupbtn.setVisible(false);
                        }
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

    public void filter(ActionEvent event) throws IOException {
        ArrayList<Integer> elternlist = new ArrayList<>();
        ArrayList<Integer> klasselist = new ArrayList<>();
        ArrayList<Integer> lehrerlist = new ArrayList<>();
        vboxEltern.getChildren().forEach(x->{
            CheckBox ch = (CheckBox)x;
            if(ch.isSelected()){
                elternlist.add(Integer.parseInt(x.getId()));
            }
        });
        vboxLehrer.getChildren().forEach(x->{
            CheckBox ch = (CheckBox)x;
            if(ch.isSelected()){
                lehrerlist.add(Integer.parseInt(x.getId()));
            }
        });
        vboxKlasse.getChildren().forEach(x->{
            CheckBox ch = (CheckBox)x;
            if(ch.isSelected()){
                klasselist.add(Integer.parseInt(x.getId()));
                System.out.println(x.getId());
            }
        });
        klasselist.forEach(System.out::println);
        Predicate<Kinder> kinderPredicate = (Kinder element)->{
            if(klasselist.contains(element.getKlasseAsObj().getKlasseId())){
                return true;
            }
            if(elternlist.contains(element.getEltern().getElternId())){
                return true;
            }if(lehrerlist.contains(element.getKlasseAsObj().getLehrerId())){
                return true;
            }if(lehrerlist.size() == 0 && klasselist.size()==0&&elternlist.size()==0){
                return true;
            }
          return false;
        };
        refreshKinder(kinderPredicate);
    }
    public void populateFilter(){
        ArrayList<Eltern> elternArrayList= DB.geteltern();
        ArrayList<Lehrer> lehrerArrayList = DB.getLehrer();
        ArrayList<Klasse> klasseArrayList = DB.getClassrooms();
        vboxKlasse.getChildren().clear();
        vboxEltern.getChildren().clear();
        vboxLehrer.getChildren().clear();
        scrollbarOfEltern.setFitToWidth(true);
        elternArrayList.forEach(x->{
            CheckBox ch = new CheckBox();
            ch.setText(x.toString());
            ch.setId(Integer.toString(x.getElternId()));
            vboxEltern.getChildren().add(ch);
        });
        lehrerArrayList.forEach(x->{
            CheckBox ch= new CheckBox();
            ch.setId(Integer.toString(x.getLehrerId()));
            ch.setText(x.toString());
            vboxLehrer.getChildren().add(ch);
        });
        klasseArrayList.forEach(x->{
            CheckBox ch = new CheckBox();
            ch.setId(Integer.toString(x.getKlasseId()));
            ch.setText(x.toString());
            vboxKlasse.getChildren().add(ch);
        });
    }
}
