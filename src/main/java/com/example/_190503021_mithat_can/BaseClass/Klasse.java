package com.example._190503021_mithat_can.BaseClass;

import java.util.List;

public class Klasse {
    private int KlasseId;
    private String KlasseName;
    private List<Kinder> Studenten;
    private int lehrerId;
    private int KlassenGrosse;

    public String getKlasseName() {
        return KlasseName;
    }

    public void setKlasseName(String klasseName) {
        KlasseName = klasseName;
    }
    public Klasse(int _lehrer,int _grosse){
        this.lehrerId = _lehrer;
        this.KlassenGrosse = _grosse;
    }

    public int getKlasseId() {
        return KlasseId;
    }

    public void setKlasseId(int klasseId) {
        KlasseId = klasseId;
    }

    public List<Kinder> getStudenten() {
        return Studenten;
    }

    public void setStudenten(List<Kinder> studenten) {
        Studenten = studenten;
    }

    public int getLehrerId() {
        return lehrerId;
    }

    public void setLehrerId(int lehrerId) {
        this.lehrerId = lehrerId;
    }

    public int getKlassenGrosse() {
        return KlassenGrosse;
    }

    public void setKlassenGrosse(int klassenGrosse) {
        KlassenGrosse = klassenGrosse;
    }

    @Override
    public String toString() {
        return KlasseName + "("+KlassenGrosse+")";
    }
}
