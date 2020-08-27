package com.example.proba1;

public class OcenaIzvestaj {

    String sifrapredmeta;

    int ocena;

    public OcenaIzvestaj(String sifrapredmeta, int ocena) {
        this.sifrapredmeta = sifrapredmeta;
        this.ocena = ocena;
    }

    public OcenaIzvestaj(){

    }

    public String getSifrapredmeta() {
        return sifrapredmeta;
    }

    public void setSifrapredmeta(String sifrapredmeta) {
        this.sifrapredmeta = sifrapredmeta;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
