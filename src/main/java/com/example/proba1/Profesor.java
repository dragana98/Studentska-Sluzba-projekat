package com.example.proba1;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Profesor implements Serializable {

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
    protected String id_predavaca;

    //Predmeti[] predmeti;


    public Profesor() {

    }

    public Profesor(String username, String role, String ime, String prezime ,String id_predavaca ) {
        this.username = username;
        this.role = "PROFESOR";
        this.ime = ime;
        this.prezime = prezime;
        this.id_predavaca = id_predavaca;
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

    public String getId_predavaca() {
        return id_predavaca;
    }

    public void setId_predavaca(String id_predavaca) {
        this.id_predavaca = id_predavaca;
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