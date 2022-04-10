package com.example._190503021_mithat_can.BaseClass;

public class Kinder extends Person{
    private Eltern Eltern;
    public Kinder(String _vorname,String _nachname,String _burgerid,Eltern _eltern){
        super(_vorname,_nachname,_burgerid);
        this.Eltern = _eltern;
    }
    public Eltern getEltern() {
        return Eltern;
    }

    public void setEltern(Eltern eltern) {
        Eltern = eltern;
    }

    @Override
    public String toString() {
        return super.toString()+","+Eltern.getElternId() + "";
    }
}
