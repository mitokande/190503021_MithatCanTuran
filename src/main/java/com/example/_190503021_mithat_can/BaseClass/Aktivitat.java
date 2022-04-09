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

    public List<com.example._190503021_mithat_can.BaseClass.Kinder> getKinder() {
        return Kinder;
    }

    public void setKinder(List<com.example._190503021_mithat_can.BaseClass.Kinder> kinder) {
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
        return "Aktivitat{" +
                "AktivitatId=" + AktivitatId +
                ", Kinder=" + Kinder +
                ", AktivitatGrosse=" + AktivitatGrosse +
                ", AktivitatZeit='" + AktivitatZeit + '\'' +
                '}';
    }
}
