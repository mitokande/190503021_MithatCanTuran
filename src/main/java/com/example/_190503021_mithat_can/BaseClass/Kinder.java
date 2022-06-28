package com.example._190503021_mithat_can.BaseClass;

public class Kinder extends Person{
    private int kinderId;
    private Eltern Eltern;
    private int kind_alter;
    private int klasse;
    private String image;

    public Kinder(String _vorname,String _nachname,String _burgerid,Eltern _eltern,int _klasse){
        super(_vorname,_nachname,_burgerid);
        this.Eltern = _eltern;
        this.klasse = _klasse;
    }
    public Eltern getEltern() {
        return Eltern;
    }

    public void setEltern(Eltern eltern) {
        Eltern = eltern;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getKind_alter() {
        return kind_alter;
    }

    public void setKind_alter(int kind_alter) {
        this.kind_alter = kind_alter;
    }

    public int getKinderId() {
        return kinderId;
    }

    public void setKinderId(int kinderId) {
        this.kinderId = kinderId;
    }

    public int getKlasse() {
        return klasse;
    }
    public Klasse getKlasseAsObj(){
        if(this.getKlasse() != 0){
            Klasse k = DB.getKlasseSingle(this.getKlasse());
            return k;
        }
        Klasse k =new Klasse(0,0);
        k.setKlasseName("Kein Klasse");
        k.setKlasseId(0);
        return k;
    }
    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    @Override
    public String toString() {
        return this.getVorname()+" "+this.getNachname()+" || Klasse: "+this.getKlasseAsObj().getKlasseName() + " || Alter: "+this.getKind_alter();
    }
}
