package com.example._190503021_mithat_can.BaseClass;

import java.sql.*;
import java.util.ArrayList;


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
    public static ArrayList<String> getusers(){
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM users");
            ArrayList<String> arr = new ArrayList<>();
            while(set.next()){
                arr.add("Username: "+set.getString("username") + " Password:" + set.getString("password"));
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
}
