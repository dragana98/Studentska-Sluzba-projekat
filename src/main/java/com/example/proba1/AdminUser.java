package com.example.proba1;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String username;


    @Column
    protected String role;

    @Column
    protected String ime;

    @Column
    protected String prezime;

    @Column
    protected String jmbg;

    @Column
    protected String mobilni;

    @Column
    protected String fiksni;



    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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


    public AdminUser() {

    }

    public AdminUser(String username, String role, String ime, String prezime  ,String jmbg, String mobilni, String fiksni) {
        this.username = username;
        this.role = "ADMIN";
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.mobilni = mobilni;
        this.fiksni = fiksni;
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

    public String getIme() {return ime;}

    public String getPrezime() {return prezime;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
