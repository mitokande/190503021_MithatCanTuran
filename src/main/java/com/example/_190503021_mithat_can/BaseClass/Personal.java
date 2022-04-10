package com.example._190503021_mithat_can.BaseClass;

public class Personal extends Person {
    private String Benutzername;
    private String Passwort;
    private String Telefonnummer;
    private String Email;
    public Personal(String _vorname,String _nachname,String _burgerid,String _bname,String _pass,String _email){
        super(_vorname,_nachname,_burgerid);
        this.Benutzername = _bname;
        this.Passwort = _pass;
        this.Email = _email;
    }
    public String getBenutzername() {
        return Benutzername;
    }

    public void setBenutzername(String benutzername) {
        Benutzername = benutzername;
    }

    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String passwort) {
        Passwort = passwort;
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

    @Override
    public String toString() {
        return super.toString()+","+Benutzername+"," + Passwort +"," + Telefonnummer +"," + Email;
    }
}
