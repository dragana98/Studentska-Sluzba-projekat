package com.example.proba1;

public class StudentDTO {

    String ime;
    String prezime;
    String brindeksa;
    String jmbg;
    String email;
    String mobilni;
    String fiksni;
    float prosek;
    float finansije;
    int espb;
    String username;
    String password;
    String role;

    public StudentDTO(String ime, String prezime, String brindeksa, String jmbg, String email, String mobilni, String fiksni, float prosek, float finansije, int espb, String username, String password, String role) {
        this.ime = ime;
        this.prezime = prezime;
        this.brindeksa = brindeksa;
        this.jmbg = jmbg;
        this.email = email;
        this.mobilni = mobilni;
        this.fiksni = fiksni;
        this.prosek = prosek;
        this.finansije = finansije;
        this.espb = espb;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public StudentDTO()
    { }

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

    public String getBrindeksa() {
        return brindeksa;
    }

    public void setBrindeksa(String brindeksa) {
        this.brindeksa = brindeksa;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilni() {
        return mobilni;
    }

    public void setMobilni(String mobilni) {
        this.mobilni = mobilni;
    }

    public String getFiksni() {
        return fiksni;
    }

    public void setFiksni(String fiksni) {
        this.fiksni = fiksni;
    }

    public float getProsek() {
        return prosek;
    }

    public void setProsek(float prosek) {
        this.prosek = prosek;
    }

    public float getFinansije() {
        return finansije;
    }

    public void setFinansije(float finansije) {
        this.finansije = finansije;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
