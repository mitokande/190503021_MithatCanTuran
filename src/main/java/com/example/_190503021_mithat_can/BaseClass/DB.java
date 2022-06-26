package com.example._190503021_mithat_can.BaseClass;

import java.sql.*;
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
    public static ArrayList<ArrayList<String>> getkinder(){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM kinder");
            ArrayList<ArrayList<String>> arr = new ArrayList<>();
            int i = 0;
            while(set.next()){
                ArrayList<String> name = new ArrayList<>();

                name.add(set.getString("vorname") +" " + set.getString("nachname"));
                name.add("Alter:"+set.getString("kind_alter") +" Klasse:" + set.getString("klasseId"));
                int a = set.getInt("kinderId");
                name.add(Integer.toString(a));
                name.add(set.getString("image"));

                arr.add(name);
            }

            return arr;

        }catch (SQLException e){

        }
        return null;
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
            ArrayList<Klasse> arr = new ArrayList<>();
            int i = 0;
            while(set.next()){
                ResultSet lehrerSet = stmt.executeQuery("SELECT * FROM lehrer WHERE id="+set.getInt("lehrerId"));
                Lehrer lehrer = new Lehrer(lehrerSet.getInt("lehrerId"),lehrerSet.getString("vorname"),lehrerSet.getString("nachname"),lehrerSet.getString("burgerId"),lehrerSet.getString("benutzername"),lehrerSet.getString("passwort"),lehrerSet.getString("email"));
                ArrayList<String> name = new ArrayList<>();

                Klasse classroom = new Klasse(lehrer,set.getInt("klassengrose"));
                lehrer.addKlasse(classroom);
                arr.add(classroom);
            }

            return arr;

        }catch (SQLException e){

        }
        return null;
    }
    public  static  void adddummyuser(){
        try{
            stmt.executeUpdate("INSERT INTO users VALUES('aptal','dummy')");
        }
        catch (SQLException e){

        }
    }
    public static void deletekinder(int id){
        try{
            stmt.executeUpdate("DELETE FROM kinder WHERE kinderId="+id);
        }catch (SQLException e){}
    }
    public static void addkinder(String vorname,String nachname,String burgerId,int elternId,int klasseId,int alter,String image) {
        try{
            String sql = "INSERT INTO kinder (kinderId,vorname,nachname,burgerId,elternId,klasseId,kind_alter,image) VALUES(?,?,?,?,?,?,?,?) ";
            System.out.println(sql);
            Random r = new Random();
            int id =r.nextInt(500000-10);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,vorname);
            ps.setString(3,nachname);
            ps.setString(4,burgerId);
            ps.setInt(5,elternId);
            ps.setInt(6,klasseId);
            ps.setInt(7,alter);
            ps.setString(8,image);

            ps.executeUpdate();

        }catch (SQLException e){

        }
    }
}
