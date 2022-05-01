package com.example._190503021_mithat_can.BaseClass;

public class Eltern extends Person{
    private int ElternId;
    private String Telefonnummer;
    private String Email;

    public Eltern(String _vorname, String _nachname, String _burgerid, int elternId, String telefonnummer, String email, String adresse) {
        super(_vorname, _nachname, _burgerid);
        ElternId = elternId;
        Telefonnummer = telefonnummer;
        Email = email;
        Adresse = adresse;
    }

    public int getElternId() {
        return ElternId;
    }

    public void setElternId(int elternId) {
        ElternId = elternId;
    }

    private String Adresse;
    private Kinder Kinder;

    public Eltern(String _vorname,String _nachname,String _burgerid){
        super(_vorname,_nachname,_burgerid);
    }
    public void AddKinder(Kinder kinder){
        if(Kinder == null){
            setKinder(kinder);
        }
    }
    public String getTelefonnummer() {
        return Telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        Telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Kinder getKinder() {
        return Kinder;
    }

    public void setKinder(Kinder kinder) {
        Kinder = kinder;
    }

    @Override
    public String toString() {
        return this.getVorname() + " " + this.getNachname();
    }
}
