package com.example.proba1;

public class PredmetiStudentaDTO {

    String idpredmeta;
    String sifrapredmeta;
    String nazivpredmeta;
    int espb;
    String imepredavaca;

    public PredmetiStudentaDTO()
    {

    }

    public PredmetiStudentaDTO(String idpredmeta, String sifrapredmeta, String nazivpredmeta, int espb, String imepredavaca) {
        this.idpredmeta = idpredmeta;
        this.sifrapredmeta = sifrapredmeta;
        this.nazivpredmeta = nazivpredmeta;
        this.espb = espb;
        this.imepredavaca = imepredavaca;
    }

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

    public String getImepredavaca() {
        return imepredavaca;
    }

    public void setImepredavaca(String imepredavaca) {
        this.imepredavaca = imepredavaca;
    }
}
