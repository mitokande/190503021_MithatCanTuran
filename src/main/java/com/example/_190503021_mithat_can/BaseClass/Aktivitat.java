package com.example._190503021_mithat_can.BaseClass;

import java.time.LocalDate;
import java.util.List;

public class Aktivitat {
    private int AktivitatId;
    private String AktivitatName;
    private List<Kinder> Kinder;
    private int AktivitatGrosse;
    private LocalDate aktivitatdatum;

    public Aktivitat(int aktivitatId, int aktivitatGrosse,LocalDate _aktivitatdatum) {
        AktivitatId = aktivitatId;
        AktivitatGrosse = aktivitatGrosse;
        aktivitatdatum = _aktivitatdatum;
    }

    public String getAktivitatName() {
        return AktivitatName;
    }

    public void setAktivitatName(String aktivitatName) {
        AktivitatName = aktivitatName;
    }

    public void AddKinder(Kinder _kinder){
        Kinder.add(_kinder);
    }
    public void LoschenKinder(Kinder _kinder){
        Kinder.remove(_kinder);
    }

    public int getAktivitatId() {
        return AktivitatId;
    }

    public void setAktivitatId(int aktivitatId) {
        AktivitatId = aktivitatId;
    }

    public List<Kinder> getKinder() {
        return Kinder;
    }

    public void setKinder(List<Kinder> kinder) {
        Kinder = kinder;
    }

    public int getAktivitatGrosse() {
        return AktivitatGrosse;
    }

    public void setAktivitatGrosse(int aktivitatGrosse) {
        AktivitatGrosse = aktivitatGrosse;
    }

    public LocalDate getAktivitatdatum() {
        return aktivitatdatum;
    }

    public void setAktivitatdatum(LocalDate aktivitatdatum) {
        this.aktivitatdatum = aktivitatdatum;
    }

    @Override
    public String toString() {
        return AktivitatId +"," + Kinder +"," + AktivitatGrosse +"," + aktivitatdatum ;
    }
}
