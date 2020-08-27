package com.example.proba1;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String username;

    @Column
    protected String password;


    @Column
    protected String role;

    @Column
    protected String email;

    @Column
    protected String ime;

    @Column
    protected String prezime;

    @Column
    protected String brindeksa;

    @Column
    protected String jmbg;

    @Column
    protected float finansije;
    @Column
    protected String mobilni;

    @Column
    protected String fiksni;

    @Column
    protected int espb;

    @Column
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


    public User() {

    }

    public User(String username, String password, String role, String email, String ime, String prezime, String broj_indeksa, String jmbg, float finansije, String mobilni, String fiksni, int espb,float prosek) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.brindeksa = broj_indeksa;
        this.jmbg = jmbg;
        this.finansije = finansije;
        this.mobilni = mobilni;
        this.fiksni = fiksni;
        this.espb = espb;
        this.prosek = prosek;
    }

    public User(String username, String password, String email, String role, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.ime = ime;
        this.prezime = prezime;
    }

    // klon
    public User(User user) {
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }
}