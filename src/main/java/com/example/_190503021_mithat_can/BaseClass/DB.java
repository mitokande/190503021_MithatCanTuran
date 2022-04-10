package com.example._190503021_mithat_can.BaseClass;

public class DB {

    public String SAVE(Object obj){
        String Sql = "Insert Into " + obj.getClass().getSimpleName() + " values(" + obj.toString()+ ")";
        //Buraya Sql komutları gelecek
        System.out.println(Sql);
        return Sql;
    }

}
