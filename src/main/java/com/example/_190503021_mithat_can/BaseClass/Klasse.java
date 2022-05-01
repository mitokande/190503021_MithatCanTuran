package com.example._190503021_mithat_can.BaseClass;

import java.util.List;

public class Klasse {
    private int KlasseId;
    private String KlasseName;
    private List<Kinder> Studenten;
    private Lehrer Lehrer;
    private int KlassenGrosse;

    public String getKlasseName() {
        return KlasseName;
    }

    public void setKlasseName(String klasseName) {
        KlasseName = klasseName;
    }
    public Klasse(Lehrer _lehrer,int _grosse){
        this.Lehrer = _lehrer;
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

    public Lehrer getLehrer() {
        return Lehrer;
    }

    public void setLehrer(Lehrer lehrer) {
        Lehrer = lehrer;
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
