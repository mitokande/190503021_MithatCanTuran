package com.example._190503021_mithat_can.BaseClass;

import java.time.LocalDate;

public class Bezahlung {
    private int bezahlungId;
    private int zahlungId;
    private int betrag;
    private LocalDate datum;

    public Bezahlung(int bezahlungId, int zahlungId, int betrag, LocalDate datum) {
        this.bezahlungId = bezahlungId;
        this.zahlungId = zahlungId;
        this.betrag = betrag;
        this.datum = datum;
    }

    public int getBezahlungId() {
        return bezahlungId;
    }

    public void setBezahlungId(int bezahlungId) {
        this.bezahlungId = bezahlungId;
    }

    public int getZahlungId() {
        return zahlungId;
    }

    public void setZahlungId(int zahlungId) {
        this.zahlungId = zahlungId;
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
