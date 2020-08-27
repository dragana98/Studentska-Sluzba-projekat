package com.example.proba1;

public class PredmetiPrijavaDTO {

    String idpredmeta;
    String sifrapredmeta;
    String nazivpredmeta;
    int espb;
    String idpredavaca;

    public PredmetiPrijavaDTO(String idpredmeta, String sifrapredmeta, String nazivpredmeta, int espb, String idpredavaca) {

        this.idpredmeta = idpredmeta;
        this.sifrapredmeta = sifrapredmeta;
        this.nazivpredmeta = nazivpredmeta;
        this.espb = espb;
        this.idpredavaca = idpredavaca;
    }

    public PredmetiPrijavaDTO(){ }

    public String getIdpredmeta() {
        return idpredmeta;
    }

    public void setIdpredmeta(String idpredmeta) {
        this.idpredmeta = idpredmeta;
    }

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

    public String getIdpredavaca() {
        return idpredavaca;
    }

    public void setIdpredavaca(String idpredavaca) {
        this.idpredavaca = idpredavaca;
    }
}
