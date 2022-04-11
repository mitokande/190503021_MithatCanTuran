package com.example._190503021_mithat_can.BaseClass;

import java.util.List;

public class Aktivitat {
    private int AktivitatId;
    private List<Kinder> Kinder;
    private int AktivitatGrosse;
    private String AktivitatZeit;

    public Aktivitat(int aktivitatId, int aktivitatGrosse) {
        AktivitatId = aktivitatId;
        AktivitatGrosse = aktivitatGrosse;
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

    public String getAktivitatZeit() {
        return AktivitatZeit;
    }

    public void setAktivitatZeit(String aktivitatZeit) {
        AktivitatZeit = aktivitatZeit;
    }

    @Override
    public String toString() {
        return AktivitatId +"," + Kinder +"," + AktivitatGrosse +"," + AktivitatZeit ;
    }
}
