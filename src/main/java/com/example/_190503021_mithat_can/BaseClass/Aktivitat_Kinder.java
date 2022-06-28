package com.example._190503021_mithat_can.BaseClass;

public class Aktivitat_Kinder {

    private int aktivitat_kinderId;
    private int aktivitatId;
    private int kinderId;

    public Aktivitat_Kinder(int aktivitat_kinderId, int aktivitatId, int kinderId) {
        this.aktivitat_kinderId = aktivitat_kinderId;
        this.aktivitatId = aktivitatId;
        this.kinderId = kinderId;
    }

    public int getAktivitat_kinderId() {
        return aktivitat_kinderId;
    }

    public void setAktivitat_kinderId(int aktivitat_kinderId) {
        this.aktivitat_kinderId = aktivitat_kinderId;
    }

    public int getAktivitatId() {
        return aktivitatId;
    }

    public void setAktivitatId(int aktivitatId) {
        this.aktivitatId = aktivitatId;
    }

    public int getKinderId() {
        return kinderId;
    }

    public void setKinderId(int kinderId) {
        this.kinderId = kinderId;
    }
}
