package com.example._190503021_mithat_can.BaseClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemMethoden {
    private ArrayList<Aktivitat> AktivitatList;
    private ArrayList<Klasse> KlasseList = new ArrayList<>();
    private ArrayList<Kinder> KinderList = new ArrayList<>();
    private ArrayList<Eltern> ElternList = new ArrayList<>();
    private ArrayList<Zahlung> ZahlungList= new ArrayList<>();
    private ArrayList<Personal> PersonalList= new ArrayList<>();
    private ArrayList<Lehrer> LehrerList= new ArrayList<>();
    private DB db = new DB();
    //AKTIVITAT
    public  void AktivitatErstellen(Aktivitat aktivitat){
        AktivitatList.add(aktivitat);
        db.SAVE(aktivitat);
    }
    public void AktivitatLoschen(Aktivitat aktivitat){
        AktivitatList.remove(aktivitat);
        //db.delete(...);
    }
    //ZAHLUNG
    public void ZahlungErstellen(Zahlung zahlung){
        ZahlungList.add(zahlung);
        db.SAVE(zahlung);
    }
    public void ZahlungLoschen(Zahlung zahlung){
        ZahlungList.remove(zahlung);
    }
    //KLASSE
    public void KlasseErstellen(Klasse klasse){
        KlasseList.add(klasse);
        db.SAVE(klasse);
    }
    public void KlasseLoschen(Klasse klasse){
        KlasseList.remove(klasse);
    }
    //KINDER
    public void KinderErstellen(Kinder kinder){
        KinderList.add(kinder);
        db.SAVE(kinder);
    }
    public void KinderLoschen(Kinder kinder){
        KinderList.remove(kinder);
    }
    //Eltern
    public void ElternErstellen(Eltern eltern){
        ElternList.add(eltern);
        db.SAVE(eltern);
    }
    public void ElternLoschen(Eltern eltern){
        ElternList.remove(eltern);
    }
    //PERSONAL
    public void PersonalErstellen(Personal personal){
        PersonalList.add(personal);
        db.SAVE(personal);
    }
    public void PersonalLoschen(Personal personal){
        PersonalList.remove(personal);
    }
    //LEHRER
    public void LehrerErstellen(Lehrer lehrer){
        LehrerList.add(lehrer);
        db.SAVE(lehrer);
    }
    public void LehrerLoschen(Lehrer lehrer){
        LehrerList.remove(lehrer);
    }
    //LISTEN
    public void ListKlasse(){
        String SQL = "SELECT * FROM Klassen";
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
    //SEARCH
    //LISTEN
    public void SearchKlasse(Klasse klasse){
        System.out.println(KlasseList.get(KlasseList.indexOf(klasse)));
    }
    public void SearchLehrer(){
        System.out.println(Arrays.toString(LehrerList.toArray()));
    }
    public void SearchKinder(){
        System.out.println(Arrays.toString(KinderList.toArray()));
    }
    public void SearchPersonal(){
        System.out.println(Arrays.toString(PersonalList.toArray()));
    }
    public void SearchZahlung(){
        System.out.println(Arrays.toString(ZahlungList.toArray()));
    }
    public void SearchAktivitat(){
        System.out.println(Arrays.toString(AktivitatList.toArray()));
    }

}
