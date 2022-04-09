package com.example._190503021_mithat_can.BaseClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemMethoden {
    private ArrayList<Aktivitat> AktivitatList;
    private ArrayList<Klasse> KlasseList = new ArrayList<>();
    private ArrayList<Kinder> KinderList = new ArrayList<>();
    private ArrayList<Zahlung> ZahlungList= new ArrayList<>();
    private ArrayList<Personal> PersonalList= new ArrayList<>();
    private ArrayList<Lehrer> LehrerList= new ArrayList<>();

    //AKTIVITAT
    public  void AktivitatErstellen(Aktivitat aktivitat){
        AktivitatList.add(aktivitat);
    }
    public void AktivitatLoschen(Aktivitat aktivitat){
        AktivitatList.remove(aktivitat);
    }
    //ZAHLUNG
    public void ZahlungErstellen(Zahlung zahlung){
        ZahlungList.add(zahlung);
    }
    public void ZahlungLoschen(Zahlung zahlung){
        ZahlungList.remove(zahlung);
    }
    //KLASSE
    public void KlasseErstellen(Klasse klasse){
        KlasseList.add(klasse);
    }
    public void KlasseLoschen(Klasse klasse){
        KlasseList.remove(klasse);
    }
    //KINDER
    public void KinderErstellen(Kinder kinder){
        KinderList.add(kinder);
    }
    public void KinderLoschen(Kinder kinder){
        KinderList.remove(kinder);
    }
    //PERSONAL
    public void PersonalErstellen(Personal personal){
        PersonalList.add(personal);
    }
    public void PersonalLoschen(Personal personal){
        PersonalList.remove(personal);
    }
    //LEHRER
    public void LehrerErstellen(Lehrer lehrer){
        LehrerList.add(lehrer);
    }
    public void LehrerLoschen(Lehrer lehrer){
        LehrerList.remove(lehrer);
    }
    //LISTEN
    public void ListKlasse(){
        System.out.println(Arrays.toString(KlasseList.toArray()));
    }
    public void ListLehrer(){
        System.out.println(Arrays.toString(LehrerList.toArray()));
    }
    public void ListKinder(){
        System.out.println(Arrays.toString(KinderList.toArray()));
    }
    public void ListPersonal(){
        System.out.println(Arrays.toString(PersonalList.toArray()));
    }
    public void ListZahlung(){
        System.out.println(Arrays.toString(ZahlungList.toArray()));
    }
    public void ListAktivitat(){
        System.out.println(Arrays.toString(AktivitatList.toArray()));
    }

}
