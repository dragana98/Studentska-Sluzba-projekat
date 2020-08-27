package com.example.proba1;

public class ProfesorDTO {

    String idpredavaca;
    String ime;
    String prezime;
    String username;
    String password;

    public ProfesorDTO(String idpredavaca, String ime, String prezime, String username,String password) {
        this.idpredavaca = idpredavaca;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password=password;
    }

    public ProfesorDTO()
    {}

    public String getIdpredavaca() {
        return idpredavaca;
    }

    public void setIdpredavaca(String idpredavaca) {
        this.idpredavaca = idpredavaca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }
}
