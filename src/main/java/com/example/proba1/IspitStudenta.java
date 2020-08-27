package com.example.proba1;

public class IspitStudenta {

    String sifrapredmeta;
    String nazivpredmeta;
    int espb;
    int ocena;

    public IspitStudenta(String sifrapredmeta, String nazivpredmeta, int espb, int ocena) {
        this.sifrapredmeta = sifrapredmeta;
        this.nazivpredmeta = nazivpredmeta;
        this.espb = espb;
        this.ocena = ocena;
    }

    public IspitStudenta(){}

    public String getSifrapredmeta() {
        return sifrapredmeta;
    }

    public void setSifrapredmeta(String sifrapredmeta) {
        this.sifrapredmeta = sifrapredmeta;
    }

    public String getNazivpredmeta() {
        return nazivpredmeta;
    }

    public void setNazivpredmeta(String nazivpredmeta) {
        this.nazivpredmeta = nazivpredmeta;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
