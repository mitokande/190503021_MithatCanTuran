package com.example._190503021_mithat_can.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class Lehrer extends Personal{
    private int LehrerId;
    private List<Klasse> Klasse = new ArrayList<>();

    public Lehrer(int id,String _vorname, String _nachname, String _burgerid, String _bname, String _pass, String _email) {
        super(_vorname, _nachname, _burgerid, _bname, _pass, _email);
        this.LehrerId = id;
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

    public void addKlasse(Klasse klasse) {
        Klasse.add(klasse);
    }

    @Override
    public String toString() {
        return this.getVorname() + " " + this.getNachname();
    }
}
