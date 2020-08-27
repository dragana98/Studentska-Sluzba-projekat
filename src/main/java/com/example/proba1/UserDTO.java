package com.example.proba1;

public class UserDTO {

    protected Long id;

    protected String username;

    protected String password;

    protected String role;

    protected String email;

    protected String ime;

    protected String prezime;

    protected String brindeksa;

    protected String jmbg;

    protected float finansije;

    protected String mobilni;


    protected String fiksni;


    protected int espb;


    protected float prosek;



    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }


    public UserDTO() {

    }

    public UserDTO(String username, String password, String role, String email, String ime, String prezime, String brindeksa, String jmbg, float finansije, String mobilni, String fiksni, int espb,float prosek) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.brindeksa = brindeksa;
        this.jmbg = jmbg;
        this.finansije = finansije;
        this.mobilni = mobilni;
        this.fiksni = fiksni;
        this.espb = espb;
        this.prosek = prosek;
    }

    public UserDTO(String username, String password, String email, String role, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.ime = ime;
        this.prezime = prezime;
    }

    // klon
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.email = user.getEmail();
    }


    public void setBrindeksa(String brindeksa)
    {
        this.brindeksa = brindeksa;
    }

    public String getBrindeksa()
    {
        return brindeksa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIme() {return ime;}

    public String getPrezime() {return prezime;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail()
    {
        return email;
    }


}