package com.example._190503021_mithat_can.BaseClass;

public class Zahlung {
    private int ZahlungID;
    private Personal ZahlungPersonal;
    private String ZahlungsArt;
    private String KartInformation;
    private Kinder Kinder;
    private Eltern Eltern;
    private int ZahlungPreis;

    public Zahlung(Kinder _kind,int _preis,String _kart){
        this.Kinder = _kind;
        this.ZahlungPreis = _preis;
        this.KartInformation = _kart;
    }
    public int getZahlungID() {
        return ZahlungID;
    }

    public void setZahlungID(int zahlungID) {
        ZahlungID = zahlungID;
    }

    public Personal getZahlungPersonal() {
        return ZahlungPersonal;
    }

    public void setZahlungPersonal(Personal zahlungPersonal) {
        ZahlungPersonal = zahlungPersonal;
    }

    public String getZahlungsArt() {
        return ZahlungsArt;
    }

    public void setZahlungsArt(String zahlungsArt) {
        ZahlungsArt = zahlungsArt;
    }

    public String getKartInformation() {
        return KartInformation;
    }

    public void setKartInformation(String kartInformation) {
        KartInformation = kartInformation;
    }

    public Kinder getKinder() {
        return Kinder;
    }

    public void setKinder(Kinder kinder) {
        Kinder = kinder;
    }

    public Eltern getEltern() {
        return Eltern;
    }

    public void setEltern(Eltern eltern) {
        Eltern = eltern;
    }

    public int getZahlungPreis() {
        return ZahlungPreis;
    }

    public void setZahlungPreis(int zahlungPreis) {
        ZahlungPreis = zahlungPreis;
    }

    @Override
    public String toString() {
        return ZahlungID +","+ ZahlungPersonal +"," + ZahlungsArt  +"," + KartInformation + "," + Kinder +"," + Eltern +"," + ZahlungPreis;
    }
}
