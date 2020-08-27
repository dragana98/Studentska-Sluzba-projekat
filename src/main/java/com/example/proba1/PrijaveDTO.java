package com.example.proba1;

public class PrijaveDTO {
    
    String sifrapredmeta;
    String idprijave;
    String brindeksa;
    int espb;
    String nazivpredmeta;

    public PrijaveDTO(String sifrapredmeta, String idprijave, String brindeksa, int espb, String nazivpredmeta) {
        this.sifrapredmeta = sifrapredmeta;
        this.idprijave = idprijave;
        this.brindeksa = brindeksa;
        this.espb = espb;
        this.nazivpredmeta = nazivpredmeta;
    }

    public String getSifrapredmeta() {
        return sifrapredmeta;
    }

    public void setSifrapredmeta(String sifrapredmeta) {
        this.sifrapredmeta = sifrapredmeta;
    }

    public String getIdprijave() {
        return idprijave;
    }

    public void setIdprijave(String idprijave) {
        this.idprijave = idprijave;
    }

    public String getBrindeksa() {
        return brindeksa;
    }

    public void setBrindeksa(String brindeksa) {
        this.brindeksa = brindeksa;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public String getNazivpredmeta() {
        return nazivpredmeta;
    }

    public void setNazivpredmeta(String nazivpredmeta) {
        this.nazivpredmeta = nazivpredmeta;
    }
}
