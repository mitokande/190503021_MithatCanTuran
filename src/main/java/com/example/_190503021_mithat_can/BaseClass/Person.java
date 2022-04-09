package com.example._190503021_mithat_can.BaseClass;

public class Person {
    private String Vorname;
    private String Nachname;
    private String BurgerId;

    public Person(String _vorname,String _nachname,String _burgerid){
        this.Vorname = _vorname;
        this.Nachname = _nachname;
        this.BurgerId = _burgerid;
    }
    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getBurgerId() {
        return BurgerId;
    }

    public void setBurgerId(String burgerId) {
        BurgerId = burgerId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", BurgerId='" + BurgerId + '\'' +
                '}';
    }
}
