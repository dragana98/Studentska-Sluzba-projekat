package com.example.proba1;

public class GotoviIspitiDTO {

    String idprijave;
    int espb;
    String sifrapredmeta;
    String brindeksa;

    public GotoviIspitiDTO(String idprijave, int espb, String sifrapredmeta, String brindeksa) {
        this.idprijave = idprijave;
        this.espb = espb;
        this.sifrapredmeta = sifrapredmeta;
        this.brindeksa = brindeksa;
    }

    public GotoviIspitiDTO()
    {}

    public String getIdprijave() {
        return idprijave;
    }

    public void setIdprijave(String idprijave) {
        this.idprijave = idprijave;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public String getSifrapredmeta() {
        return sifrapredmeta;
    }

    public void setSifrapredmeta(String sifrapredmeta) {
        this.sifrapredmeta = sifrapredmeta;
    }

    public String getBrindeksa() {
        return brindeksa;
    }

    public void setBrindeksa(String brindeksa) {
        this.brindeksa = brindeksa;
    }
}
