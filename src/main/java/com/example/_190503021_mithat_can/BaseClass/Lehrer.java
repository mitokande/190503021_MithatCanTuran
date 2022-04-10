package com.example._190503021_mithat_can.BaseClass;

import java.util.List;

public class Lehrer extends Personal{
    private int LehrerId;
    private List<Klasse> Klasse;

    public Lehrer(String _vorname, String _nachname, String _burgerid, String _bname, String _pass, String _email) {
        super(_vorname, _nachname, _burgerid, _bname, _pass, _email);
    }

    public int getLehrerId() {
        return LehrerId;
    }

    public void setLehrerId(int lehrerId) {
        LehrerId = lehrerId;
    }

    public List<Klasse> getKlasse() {
        return Klasse;
    }

    public void setKlasse(List<Klasse> klasse) {
        Klasse = klasse;
    }

    @Override
    public String toString() {
        return super.toString()+","+LehrerId + ","+Klasse;
    }
}
