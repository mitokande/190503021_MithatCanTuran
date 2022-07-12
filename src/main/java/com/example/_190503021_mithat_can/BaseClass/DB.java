package com.example._190503021_mithat_can.BaseClass;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class DB {
    public  static Connection conn = null;
    public static Statement stmt;
    public static void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:./sqlite/sqlite-tools/kindergarten.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            stmt = conn.createStatement();
            //stmt.executeUpdate("CREATE  TABLE Klasse (KlasseId int, )")
            ResultSet res = stmt.executeQuery("SELECT * FROM users");
            while(res.next()){
                System.out.println("username: "+res.getString("username")+" Pass: "+res.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public String SAVE(Object obj) {
        String Sql = "Insert Into " + obj.getClass().getSimpleName() + " values(" + obj.toString()+ ")";
        //Buraya Sql komutları gelecek
        System.out.println(Sql);
        try {
            stmt.executeUpdate(Sql);
            ResultSet res = stmt.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Sql;
    }


    //region GetEntities
    public static ArrayList<Kinder> getkinder(){
        ArrayList<Kinder> arr = new ArrayList<>();
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM kinder");

            int i = 0;
            while(set.next()){

                Eltern e = new Eltern("Kein","Eltern","",0,"","","");

                Kinder kind = new Kinder(set.getString("vorname"),set.getString("nachname"),set.getString("burgerid"),e,set.getInt("klasseId"));
                kind.setKind_alter(set.getInt("kind_alter"));

                kind.setKinderId(set.getInt("kinderId"));
                kind.setImage(set.getString("image"));


                arr.add(kind);
            }

            for(Kinder k : arr){

                ResultSet kinderSet = stmt.executeQuery("SELECT * FROM eltern,kinder WHERE eltern.elternId=kinder.elternId AND kinderId="+k.getKinderId());
                while (kinderSet.next()){
                    k.setEltern(DB.getElternSingle(kinderSet.getInt("elternId")));
                }
                System.out.println(k.getEltern());
            }
            return arr;

        }catch (SQLException e){
            System.out.println("getkind"+e.getMessage());
        }
        return arr;
    }
    public static ArrayList<Eltern> geteltern(){
        ArrayList<Eltern> elterns = new ArrayList<>();
        String Sql = "SELECT * FROM eltern";
        try{
            ResultSet set = stmt.executeQuery(Sql);
            while(set.next()){
                elterns.add(new Eltern(set.getString("vorname"),
                        set.getString("nachname"), set.getString("burgerId"),
                        set.getInt("elternId"),set.getString("telefonnummer"),
                        set.getString("email"),set.getString("adresse")));
            }
        }catch (SQLException e){

        }
        return elterns;

    }
    public static ArrayList<Klasse> getClassrooms(){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM klasse");
            ArrayList<Klasse> klasseArrayList = new ArrayList<>();
            while(set.next()){

                Klasse classroom = new Klasse(set.getInt("lehrerId"),set.getInt("klassengrosse"));
                classroom.setKlasseName(set.getString("klasse_name"));
                classroom.setKlasseId(set.getInt("klasseId"));


                klasseArrayList.add(classroom);
            }
            set.close();
            for(Klasse k : klasseArrayList){
                ResultSet lehrerSet = stmt.executeQuery("SELECT * FROM lehrer WHERE lehrerId="+k.getLehrerId());
                while(lehrerSet.next()){
                    Lehrer lehrer = new Lehrer(lehrerSet.getInt("lehrerId"),lehrerSet.getString("vorname"),lehrerSet.getString("nachname"),lehrerSet.getString("burgerId"),lehrerSet.getString("benutzername"),lehrerSet.getString("passwort"),lehrerSet.getString("email"));
                    lehrer.addKlasse(k);
                }
            }
            return klasseArrayList;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static ArrayList<Lehrer> getLehrer(){
        try{
            ArrayList<Lehrer> lehrerArrayList = new ArrayList<>();
            ResultSet set = stmt.executeQuery("SELECT  * FROM lehrer");
            while(set.next()){
                Lehrer lehrer = new Lehrer(set.getInt("lehrerId"),set.getString("vorname"),set.getString("nachname"),set.getString("burgerId"),set.getString("benutzername"),set.getString("passwort"),set.getString("email"));
                lehrerArrayList.add(lehrer);
            }
            set.close();
            for(Lehrer l : lehrerArrayList){
                String sql = "SELECT * FROM klasse WHERE klasse.lehrerId="+l.getLehrerId() ;
                ResultSet klasseSet = stmt.executeQuery(sql);
                while (klasseSet.next()){
                    Klasse klasse = new Klasse(l.getLehrerId(),klasseSet.getInt("klassengrosse"));
                    klasse.setKlasseName(klasseSet.getString("klasse_name"));
                    l.addKlasse(klasse);

                }
            }
            return lehrerArrayList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static ArrayList<Aktivitat> getAktivitat(){
        try{
            ArrayList<Aktivitat> aktivitatArrayList = new ArrayList<>();
            ResultSet set = stmt.executeQuery("SELECT * FROM aktivitat");
            while (set.next()){
                Aktivitat aktivitat = new Aktivitat(set.getInt("aktivitatId"),set.getInt("aktivitatgrosse"),set.getDate("aktivitatdatum").toLocalDate());
                aktivitat.setAktivitatName(set.getString("aktivitatname"));
                aktivitat.setAktivitatdatum(set.getDate("aktivitatdatum").toLocalDate());
                aktivitatArrayList.add(aktivitat);
            }
            return aktivitatArrayList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static ArrayList<Zahlung> getZahlungen(){
        ArrayList<Zahlung> zahlungs = new ArrayList<>();
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM zahlung");
            while (set.next()){
                Zahlung zahlung = new Zahlung(
                        set.getInt("zahlungId"), set.getInt("personalId"),
                        set.getInt("elternId"), set.getInt("kinderId"),
                        set.getInt("gesamtesumme"), set.getInt("gezahlterBetrag"));
                zahlungs.add(zahlung);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  zahlungs;
    }
    //endregion

    //region GetSingleEntity
    public static Lehrer getLehrerSingle(int Id) {
        try{
            ResultSet set = stmt.executeQuery("SELECT  * FROM lehrer WHERE lehrerId="+Id);
            if(set.next()){
                Lehrer lehrer = new Lehrer(set.getInt("lehrerId"),set.getString("vorname"),set.getString("nachname"),set.getString("burgerId"),set.getString("benutzername"),set.getString("passwort"),set.getString("email"));
                return lehrer;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Kinder getKinderSingle(int id){
        try{
            ResultSet kinderSet = stmt.executeQuery("SELECT * FROM kinder WHERE kinderId="+id);
            if(kinderSet.next()){
                System.out.println("??????"+kinderSet.getInt("klasseId"));
                Kinder k = new Kinder(kinderSet.getString("vorname"),kinderSet.getString("nachname"), kinderSet.getString("burgerId"),null,kinderSet.getInt("klasseId"));
                k.setKind_alter(kinderSet.getInt("kind_alter"));
                k.setKinderId(kinderSet.getInt("kinderId"));
                System.out.println(k.getKinderId()+"single");
                k.setImage(kinderSet.getString("image"));
                k.setEltern(DB.getElternSingle(kinderSet.getInt("elternId")));
                return k;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Aktivitat getAktivitatSingle(int id){
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM aktivitat WHERE aktivitatId="+id);
            while (set.next()){
                Aktivitat aktivitat = new Aktivitat(set.getInt("aktivitatId"),set.getInt("aktivitatgrosse"), set.getDate("aktivitatdatum").toLocalDate());
                aktivitat.setAktivitatName(set.getString("aktivitatname"));
                return aktivitat;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Eltern getElternSingle(int id){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM eltern WHERE elternId="+id);
            if(set.next()){
                Eltern e = new Eltern(set.getString("vorname"),
                        set.getString("nachname"), set.getString("burgerId"),
                        set.getInt("elternId"),set.getString("telefonnummer"),
                        set.getString("email"),set.getString("adresse"));
                return e;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Klasse getKlasseSingle(int id){
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM klasse WHERE klasseId="+id);
            while (set.next()){
                Klasse k = new Klasse(set.getInt("lehrerId"),set.getInt("klassengrosse"));
                k.setKlasseId(set.getInt("klasseId"));
                k.setKlasseName(set.getString("klasse_name"));
                return k;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Zahlung getZahlungSingle(int id){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM zahlung WHERE zahlungId="+id);
            if(set.next()){
                Zahlung zahlung = new Zahlung(
                        set.getInt("zahlungId"), set.getInt("personalId"),
                        set.getInt("elternId"), set.getInt("kinderId"),
                        set.getInt("gesamtesumme"), set.getInt("gezahlterBetrag"));
                return zahlung;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //endregion

    //region SaveSignleEntity
    public static void saveAktivitat(Aktivitat aktivitat){
        try{
            String sql = "UPDATE aktivitat SET aktivitatname=?,aktivitatgrosse=?,aktivitatdatum=? WHERE aktivitatId="+aktivitat.getAktivitatId();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,aktivitat.getAktivitatName());
            ps.setInt(2,aktivitat.getAktivitatGrosse());
            ps.setDate(3,Date.valueOf(aktivitat.getAktivitatdatum()));
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveKinder(Kinder kinder) {
        try {
            String sql = "UPDATE kinder SET vorname=?,nachname=?,kind_alter=?,burgerId=?,elternId=?,klasseId=?,image=? WHERE kinderId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(kinder.getBurgerId()+"bur");
            ps.setString(1,kinder.getVorname());
            ps.setString(2,kinder.getNachname());
            ps.setInt(3,kinder.getKind_alter());
            ps.setString(4,kinder.getBurgerId());
            ps.setInt(5,kinder.getEltern().getElternId());
            ps.setInt(6,kinder.getKlasse());
            ps.setString(7,kinder.getImage());
            ps.setInt(8,kinder.getKinderId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveKlasse(Klasse k) {
        try{
            System.out.println(k.getKlasseId());
            PreparedStatement ps = conn.prepareStatement("UPDATE klasse SET klasse_name=?,klassengrosse=?,lehrerId=? WHERE klasseId=?");
            ps.setString(1,k.getKlasseName());
            ps.setInt(2,k.getKlassenGrosse());
            ps.setInt(3,k.getLehrerId());
            ps.setInt(4,k.getKlasseId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveLehrer(Lehrer lehrer) {
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE lehrer SET vorname=?,nachname=?,burgerId=?,benutzername=?,passwort=?,email=? WHERE lehrerId=?");
            ps.setString(1,lehrer.getVorname());
            ps.setString(2,lehrer.getNachname());
            ps.setString(3, lehrer.getBurgerId());
            ps.setString(4, lehrer.getBenutzername());
            ps.setString(5, lehrer.getPasswort());
            ps.setString(6,lehrer.getEmail());
            ps.setInt(7,lehrer.getLehrerId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveEltern(Eltern eltern){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE eltern SET vorname=?,nachname=?,burgerId=?,telefonnummer=?,email=?,adresse=? WHERE elternId=?");
            ps.setString(1,eltern.getVorname());
            ps.setString(2,eltern.getNachname());
            ps.setString(3, eltern.getBurgerId());
            ps.setString(4, eltern.getTelefonnummer());
            ps.setString(5, eltern.getEmail());
            ps.setString(6,eltern.getAdresse());
            ps.setInt(7,eltern.getElternId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion

    //region AddNewEntity
    public static void addkinder(Kinder kinder) {
        try{
            String sql = "INSERT INTO kinder (vorname,nachname,burgerId,elternId,klasseId,kind_alter,image) VALUES(?,?,?,?,?,?,?) ";
            System.out.println(sql);
            Random r = new Random();
            int id =r.nextInt(500000-10);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,kinder.getVorname());
            ps.setString(2,kinder.getNachname());
            ps.setString(3,kinder.getBurgerId());
            ps.setInt(4,kinder.getEltern().getElternId());
            ps.setInt(5,kinder.getKlasse());
            ps.setInt(6,kinder.getKind_alter());
            ps.setString(7,kinder.getImage());

            ps.executeUpdate();

        }catch (SQLException e){

        }
    }
    public static void AddClassroom(Klasse classroom){
        try {
            String sql = "INSERT INTO klasse (lehrerId,klassengrosse,klasse_name) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,classroom.getLehrerId());
            ps.setInt(2,classroom.getKlassenGrosse());
            ps.setString(3,classroom.getKlasseName());
            ps.executeUpdate();
        }catch (SQLException e){

        }
    }
    public static void addEltern(Eltern eltern) {
        try {
            String sql = "INSERT INTO eltern (vorname,nachname,burgerid,telefonnummer,email,adresse) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,eltern.getVorname());
            ps.setString(2,eltern.getNachname());
            ps.setString(3,eltern.getBurgerId());
            ps.setString(4,eltern.getTelefonnummer());
            ps.setString(5,eltern.getEmail());
            ps.setString(6,eltern.getAdresse());
            ps.executeUpdate();
        }catch (SQLException e){

        }
    }
    public static void AddLehrer(Lehrer lehrer){
        try{
            String sql = "INSERT INTO lehrer (vorname,nachname,burgerId,benutzername,passwort,email) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,lehrer.getVorname());
            ps.setString(2,lehrer.getNachname());
            ps.setString(3,lehrer.getBurgerId());
            ps.setString(4,lehrer.getBenutzername());
            ps.setString(5,lehrer.getPasswort());
            ps.setString(6,lehrer.getEmail());
            ps.executeUpdate();


        }catch (SQLException e){

        }
    }
    public static void AddAktivitat(Aktivitat aktivitat){
        try{
            String sql= "INSERT INTO aktivitat (aktivitatname,aktivitatgrosse,aktivitatdatum) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Date date = Date.valueOf(aktivitat.getAktivitatdatum());
            ps.setString(1,aktivitat.getAktivitatName());
            ps.setDate(3,date);
            ps.setInt(2,aktivitat.getAktivitatGrosse());
            ps.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void AddZahlung(Zahlung zahlung){
        try{
            String sql = "INSERT INTO zahlung (elternId,kinderId,personalId,gesamtesumme,gezahlterBetrag) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,zahlung.getElternId());
            ps.setInt(2,zahlung.getKinderId());
            ps.setInt(3,zahlung.getPersonalId());
            ps.setInt(4,zahlung.getGesamtesumme());
            ps.setInt(5,zahlung.getGezahlterBetrag());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void AddBezahlung(Bezahlung bezahlung){
        try{
            String sql="INSERT INTO bezahlung (zahlungId,betrag,datum) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bezahlung.getBezahlungId());
            ps.setInt(2,bezahlung.getBetrag());
            ps.setDate(3,Date.valueOf(bezahlung.getDatum()));
            ps.executeUpdate();
            sql = "UPDATE zahlung SET gezahlterBetrag=gezahlterBetrag+? WHERE zahlungId=?";
            PreparedStatement psZ = conn.prepareStatement(sql);
            psZ.setInt(1,bezahlung.getBetrag());
            psZ.setInt(2,bezahlung.getZahlungId());
            psZ.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion

    //region DeleteEntity
    public static void deleteKinder(int id){
        try{
            stmt.executeUpdate("DELETE FROM kinder WHERE kinderId="+id);
        }catch (SQLException e){}
    }
    public static void deleteKlasse(int id){
        try{
            stmt.executeUpdate("DELETE FROM klasse WHERE klasseId="+id);
        }catch (SQLException e){}
    }
    public static void deleteLehrer(int id){
        try{
            stmt.executeUpdate("DELETE FROM lehrer WHERE lehrerId="+id);
            stmt.executeUpdate("UPDATE klasse SET lehrerId=NULL WHERE lehrerId="+id);
        }catch (SQLException e){}
    }
    public static void deleteAktivitat(int id){
        try {
            stmt.executeUpdate("DELETE FROM aktivitat WHERE aktivitatId="+id);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void deleteEltern(int id){
        try{
            stmt.executeUpdate("DELETE FROM eltern WHERE elternId="+id);
            stmt.executeUpdate("UPDATE kinder SET elternId=NULL WHERE elternId="+id);
        }catch (SQLException e){}
    }
    //endregion

    //region AktivitatBehavivors
    public static ArrayList<Kinder> getKinderVomAktivitat(Aktivitat aktivitat){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM aktivitat_kinder WHERE aktivitatId="+aktivitat.getAktivitatId());
            ArrayList<Aktivitat_Kinder> aktivitatKinderArrayList = new ArrayList<>();
            ArrayList<Kinder> kinderArrayList = new ArrayList<>();
            while (set.next()){
                Aktivitat_Kinder ak = new Aktivitat_Kinder(set.getInt("aktivitat_kinderId"),set.getInt("aktivitatId"),set.getInt("kinderId"));
                aktivitatKinderArrayList.add(ak);
            }

            for(Aktivitat_Kinder ak : aktivitatKinderArrayList){
                ResultSet kinderSet = stmt.executeQuery("SELECT * FROM kinder WHERE kinderId="+ak.getKinderId());
                if(kinderSet.next()){
                    Kinder k = new Kinder(kinderSet.getString("vorname"),kinderSet.getString("nachname"), kinderSet.getString("burgerId"),null,kinderSet.getInt("klasseId"));
                    k.setKind_alter(kinderSet.getInt("kind_alter"));
                    k.setKinderId(kinderSet.getInt("kinderId"));
                    k.setImage(kinderSet.getString("image"));
                    k.setEltern(DB.getElternSingle(kinderSet.getInt("elternId")));

                    kinderArrayList.add(k);
                }
            }
            return kinderArrayList;
        }catch (SQLException e){
            System.out.println("getKinderVomAktivitat"+e.getMessage());
        }
        return null;
    }
    public static void addKinderZumAktivitat(Aktivitat_Kinder aktivitat_kinder){
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM aktivitat_kinder WHERE aktivitatId="+aktivitat_kinder.getAktivitatId() + " AND kinderId="+aktivitat_kinder.getKinderId());
            int id= 0;
            if(set.next()){
                id = set.getInt("aktivitat_kinderId");
            }
            String sql = "INSERT INTO aktivitat_kinder (aktivitatId,kinderId) VALUES (?,?)";
            if(id!=0){
                sql = "UPDATE aktivitat_kinder SET aktivitatId=?,kinderId=? WHERE aktivitat_kinderId="+id;

            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,aktivitat_kinder.getAktivitatId());
            ps.setInt(2,aktivitat_kinder.getKinderId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("addKinderZumAktivitat"+e.getMessage());
        }
    }
    public static void deleteKinderVomAktivitat(int kinderId,int aktivitatId){
        try {
            stmt.executeUpdate("DELETE FROM aktivitat_kinder WHERE aktivitatId="+aktivitatId + " AND kinderId="+kinderId);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion

    //region KlasseBehaivors
    public static ArrayList<Kinder> getKinderVomKlasse(Klasse klasse){
        ArrayList<Kinder> arr = new ArrayList<>();
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM kinder,eltern WHERE eltern.elternId = kinder.elternId AND klasseId="+klasse.getKlasseId());

            int i = 0;
            while(set.next()){

                Eltern eltern = null;
                eltern = new Eltern(set.getString(10),set.getString(11),set.getString("burgerid"),set.getInt("elternId"),set.getString("telefonnummer"),set.getString("email"),set.getString("adresse"));


                Kinder kind = new Kinder(set.getString("vorname"),set.getString("nachname"),set.getString("burgerid"),eltern,set.getInt("klasseId"));
                kind.setKind_alter(set.getInt("kind_alter"));

                kind.setKinderId(set.getInt("kinderId"));
                kind.setImage(set.getString("image"));


                arr.add(kind);
            }

            return arr;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
    public static void addKinderZumKlasse(int kinderId,int klasseId){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE kinder SET klasseId=? WHERE kinderId=?");
            ps.setInt(1,klasseId);
            ps.setInt(2,kinderId);
            ps.executeUpdate();
        }catch (SQLException e){

        }
    }
    public static void deleteKinderVomKlasse(int kinderId){
        try{
            stmt.executeUpdate("UPDATE kinder SET klasseId=0 WHERE kinderId="+kinderId);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion

    public static ArrayList<Bezahlung> getBezahlungVomZahlung(int zahlungId){
        ArrayList<Bezahlung> bezahlungs = new ArrayList<>();
        try {
            ResultSet set = stmt.executeQuery("SELECT * FROM bezahlung WHERE zahlungId="+zahlungId);
            while (set.next()){
                Bezahlung bezahlung = new Bezahlung(set.getInt("bezahlungId"),set.getInt("zahlungId"),set.getInt("betrag"),set.getDate("datum").toLocalDate());
                bezahlungs.add(bezahlung);
            }
            return bezahlungs;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bezahlungs;
    }
}
