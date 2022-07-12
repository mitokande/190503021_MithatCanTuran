package com.example._190503021_mithat_can.BaseClass;

public class Zahlung {
    private int zahlungId;
    private int personalId;
    private int elternId;
    private int kinderId;
    private int gesamtesumme;
    private int gezahlterBetrag;

    public Zahlung(int zahlungId, int personalId, int elternId, int kinderId, int gesamtesumme, int gezahlterBetrag) {
        this.zahlungId = zahlungId;
        this.personalId = personalId;
        this.elternId = elternId;
        this.kinderId = kinderId;
        this.gesamtesumme = gesamtesumme;
        this.gezahlterBetrag = gezahlterBetrag;
    }

    public int getZahlungId() {
        return zahlungId;
    }

    public void setZahlungId(int zahlungId) {
        this.zahlungId = zahlungId;
    }

    public int getPersonalId() {
        return personalId;
    }

    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public int getElternId() {
        return elternId;
    }

    public void setElternId(int elternId) {
        this.elternId = elternId;
    }

    public int getKinderId() {
        return kinderId;
    }

    public void setKinderId(int kinderId) {
        this.kinderId = kinderId;
    }

    public int getGesamtesumme() {
        return gesamtesumme;
    }

    public void setGesamtesumme(int gesamtesumme) {
        this.gesamtesumme = gesamtesumme;
    }

    public int getGezahlterBetrag() {
        return gezahlterBetrag;
    }

    public void setGezahlterBetrag(int gezahlterBetrag) {
        this.gezahlterBetrag = gezahlterBetrag;
    }

}
