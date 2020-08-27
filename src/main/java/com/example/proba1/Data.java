package com.example.proba1;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Data {

    Connection conn = null;
    Statement stmt = null;
    String sql = null;

    /**
     * Povezivanje sa bazom
     */

    public Data(){

       // System.out.println("Connecting . .. . .");

        try{
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/studentskabaza",
                    "root",
                    ""
            );
          //  System.out.println("Connected .");

            stmt = conn.createStatement();

        } catch (SQLException throwables) {
            
            throwables.printStackTrace();
        }
    }

    /**
     *              Logovanje
     * @param username username korisnika
     * @param password sifra korisnika
     * @return User
     */
    public User usFun(String username,String password)
    {
        sql = "SELECT * FROM users WHERE username= '" + username + "' AND password='" + password + "'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
              return new User(username,password,rs.getString("email"),rs.getString("role"),rs.getNString("ime"),rs.getString("prezime"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Funkcija vraca ulogovanog studenta
     * @param username username ulogovanog korisnika
     * @return student
     */
    public User vratiUser(String username)
    {
        sql = "SELECT * FROM student WHERE username= '" + username + "'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
                return new User(username,rs.getString("password"),rs.getString("role"),rs.getString("email"),rs.getString("ime"),rs.getString("prezime"),rs.getString("broj_indeksa"),rs.getString("jmbg"),rs.getFloat("finansije"),rs.getString("mobilni"),rs.getString("fiksni"),rs.getInt("espb"),rs.getFloat("prosek"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Funkcija vraca ulogovanog admina
     * @param username username ulogovanog korisnika
     * @return admin
     */
    public AdminUser vratiAdmin(String username)
    {
        sql = "SELECT * FROM admins WHERE username= '" + username + "'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
                return new AdminUser(username,"ADMIN",rs.getString("ime"),rs.getString("prezime"),rs.getString("jmbg"),rs.getString("mobilni"),rs.getString("fiksni"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Funkcija vraca ulogovanog profesora
     * @param username username korisnika koji se ulogovao
     * @return profesor
     */
    public Profesor vratiProfesora(String username)
    {
        sql = "SELECT * FROM predavaci WHERE username= '" + username + "'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
                return new Profesor(username,"PROFESOR",rs.getString("ime"),rs.getString("prezime"),rs.getString("id_predavaca"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Salje zahtev za novu sifru
     * @param username trenutni korisnik
     *
     */
    public ForgottenPassDTO posaljiZahtev(String username)
    {
        sql = "INSERT INTO lozinka "
                + "(username)"
                + " VALUES ('"+username+"') ";

        try {
            stmt.execute(sql);

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Brise studente(User) ili profesore iz baze
     * iz tabele student/predavaci  i users
     * @param role STUDENT/PROFESOR
     *
     */
    public String brisanjeKorisnika(String username,String role)
    {
        String nadjen="ne";

        // STUDENT
        if(role.equals("STUDENT")) {

            sql = "SELECT * FROM student WHERE username= '" + username + "'";

            ResultSet rs =null;

            try{
                rs = stmt.executeQuery(sql);

                if(rs.first())
                {
                    nadjen="da";

                    sql = "DELETE FROM student "
                            + "WHERE username= '"+ username +"'";

                    try {
                        stmt.execute(sql);
                    } catch(SQLException throwables){
                        throwables.printStackTrace();
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }//  PORFESOR
        else
        {
            sql = "SELECT * FROM predavaci WHERE username= '" + username + "'";

            ResultSet rs =null;

            try{
                rs = stmt.executeQuery(sql);

                if(rs.first())
                {
                    nadjen="da";

                    sql = "DELETE FROM predavaci "
                            + "WHERE username= '"+ username +"'";

                    try {
                        stmt.execute(sql);

                    } catch(SQLException throwables){
                        throwables.printStackTrace();
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        sql = "SELECT * FROM users WHERE username= '" + username + "'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
            {
                nadjen="da";

                sql = "DELETE FROM users "
                        + "WHERE username= '"+ username +"'";


                try {
                    stmt.execute(sql);
                } catch(SQLException throwables){
                    throwables.printStackTrace();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return nadjen;
    }

    /**
     * Dodaje profesora u bazu ako ne postoji
     * @param ime
     * @param prezime
     * @param idpredavaca
     * @param username
     * @param password
     */
    public String unesiProfesora(String ime,String prezime,String idpredavaca,String username,String password)
    {
        String role="PROFESOR";
        String email = "profesor@gmail.com";
        
        String nadjen="da";

        sql = "SELECT * FROM predavaci WHERE username= '" + username + "' OR id_predavaca='" + idpredavaca+"'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
            {
                return nadjen;
            }
            else
                nadjen="ne";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "INSERT INTO predavaci "
                + "(id_predavaca, ime, prezime, username)"
                + " VALUES ('"+idpredavaca+"','"+ime+"','"+prezime+"','"+username+"') ";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }


        sql = "INSERT INTO users "
                + "(username, password, email, role, ime, prezime)"
                + " VALUES ('"+username+"','"+password+"','"+email+"','"+role+"','"+ime+"','"+prezime+"')";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

    return nadjen;
    }

    /**
     * Dodaje studenta u bazu ako ne postoji
     * @param ime
     * @param prezime
     * @param brindeksa
     * @param jmbg
     * @param prosek
     * @param finansije
     * @param espb
     * @param email
     * @param role
     * @param fiksni
     * @param mobilni
     * @param username
     * @param password
     * @return
     */
    public String dodajStudenta(String ime,String prezime,String brindeksa,String jmbg,float prosek,float finansije,int espb,String email,String role,String fiksni,String mobilni,String username,String password)
    {
        String nadjen="da";

        sql = "SELECT * FROM student WHERE username= '" + username + "' OR broj_indeksa='"+brindeksa+"'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);
            if(rs.first())
            {
                return nadjen;
            }
            else
                nadjen="ne";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        sql = "INSERT INTO student "
                + "(broj_indeksa, ime, prezime, jmbg, email, mobilni, fiksni, prosek, espb, username, password, finansije, role)"
                + " VALUES ('"+brindeksa+"','"+ime+"','"+prezime+"','"+jmbg+"','"+email+"','"+mobilni+"','"+fiksni+"','"+prosek+"','"+espb+"','"+username+"','"+password+"','"+finansije+"','"+role+"') ";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }


        sql = "INSERT INTO users "
                + "(username, password, email, role, ime, prezime)"
                + " VALUES ('"+username+"','"+password+"','"+email+"','"+role+"','"+ime+"','"+prezime+"')";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return nadjen;

    }

    /**
     * Daje listu profesora
     * @return
     */
    public ArrayList<ProfesorDTO> profesoriJson()
    {
        sql = "SELECT * FROM predavaci";

        ResultSet rs =null;

        ArrayList<ProfesorDTO> listaProfesora = new ArrayList<ProfesorDTO>();

        try{
            rs = stmt.executeQuery(sql);

            File json = new File("C:\\Users\\Dragana\\Desktop\\PROJEKAT proba2\\proba1\\ispisprofesora\\profesor.json");
            ObjectMapper om = new ObjectMapper();

            while(rs.next())
            {
                String idpredavaca = rs.getString("id_predavaca");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String username = rs.getString("username");

                ProfesorDTO p = new ProfesorDTO(idpredavaca,ime,prezime,username,"password");

                listaProfesora.add(p);
            }
            for(int i=0;i<listaProfesora.size();i++)
            {
                om.writeValue(json,listaProfesora);
               // System.out.println(listaProfesora.get(i).getIme());
            }

        } catch (SQLException | JsonMappingException throwables) {
            throwables.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaProfesora;

    }

    /**
     * Ispisuje prijave
     * @return
     */
    public ArrayList<PrijaveDTO> prijaveIspis()
    {
        sql = "SELECT * FROM prijave";

        ResultSet rs =null;

        ArrayList<PrijaveDTO> listaPrijava = new ArrayList<PrijaveDTO>();

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String idprijave = rs.getString("id_prijave");
                String nazivpredmeta = rs.getString("naziv_predmeta");
                String sifrapredmeta = rs.getString("sifra_predmeta");
                String brindeksa = rs.getString("broj_indeksa");
                int espb = rs.getInt("espb");

                PrijaveDTO p = new PrijaveDTO(sifrapredmeta,idprijave,brindeksa,espb,nazivpredmeta);

                listaPrijava.add(p);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaPrijava;

    }

    /**
     * Ispisuje predmete koji se mogu prijaviti za polaganje
     * @return
     */
    public ArrayList<PredmetiPrijavaDTO> predmetizaprijavu()
    {
        sql = "SELECT * FROM predmeti";

        ResultSet rs =null;

        ArrayList<PredmetiPrijavaDTO> listaPredmeta = new ArrayList<PredmetiPrijavaDTO>();

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String idpredmeta = rs.getString("id_predmeta");
                String nazivpredmeta = rs.getString("naziv_predmeta");
                String sifrapredmeta = rs.getString("sifra_predmeta");
                String idpredavaca = rs.getString("id_predavaca");
                int espb = rs.getInt("espb");

                PredmetiPrijavaDTO p = new PredmetiPrijavaDTO(idpredmeta,sifrapredmeta,nazivpredmeta,espb,idpredavaca);

                listaPredmeta.add(p);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaPredmeta;

    }

    /**
     * Ispisuje predmete studenta iz baze
     * @return
     */
    public ArrayList<PredmetiStudentaDTO> predmetiStudenta()
    {
        sql = "SELECT * FROM predmeti";

        ResultSet rs =null;

        // svi predmeti
        ArrayList<PredmetiPrijavaDTO> listaPredmeta = new ArrayList<PredmetiPrijavaDTO>();

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String idpredmeta = rs.getString("id_predmeta");
                String nazivpredmeta = rs.getString("naziv_predmeta");
                String sifrapredmeta = rs.getString("sifra_predmeta");
                String idpredavaca = rs.getString("id_predavaca");
                int espb = rs.getInt("espb");

                PredmetiPrijavaDTO p = new PredmetiPrijavaDTO(idpredmeta,sifrapredmeta,nazivpredmeta,espb,idpredavaca);

                listaPredmeta.add(p);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // profesori

        sql = "SELECT * FROM predavaci";

        rs =null;

        ArrayList<ProfesorDTO> listaProfesora = new ArrayList<ProfesorDTO>();

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String idpredavaca = rs.getString("id_predavaca");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String username = rs.getString("username");

                ProfesorDTO pp = new ProfesorDTO(idpredavaca,ime,prezime,username,"password");

                listaProfesora.add(pp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<PredmetiStudentaDTO> predmetiStudenta = new ArrayList<PredmetiStudentaDTO>();

        PredmetiStudentaDTO predmeti;

        for(PredmetiPrijavaDTO pr : listaPredmeta)
        {
            for(ProfesorDTO prof : listaProfesora)
            {
                if((pr.getIdpredavaca()).equals(prof.getIdpredavaca()))
                {
                    predmeti = new PredmetiStudentaDTO(pr.getIdpredmeta(),pr.getSifrapredmeta(),pr.getNazivpredmeta(),pr.getEspb(),prof.getIme()+" "+prof.getPrezime());

                    predmetiStudenta.add(predmeti);
                }
            }
        }

        return predmetiStudenta;
    }

    /**
     * Prijavljivanje ispita
     * @param brindeksa
     * @param sifrapredmeta
     */
    public void prijavljujemIspit(String brindeksa,String sifrapredmeta)
    {
        sql = "SELECT * FROM predmeti WHERE sifra_predmeta='"+sifrapredmeta+"'";

        String nazivpredmeta = "Predmet nije na spisku";
        String idpredavaca = "Id predavaca nije na spisku";
        int espb = 0;

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
            {
                 nazivpredmeta = rs.getString("naziv_predmeta");
                 idpredavaca = rs.getString("id_predavaca");
                 espb = rs.getInt("espb");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "INSERT INTO prijave "
                + "(broj_indeksa, sifra_predmeta, naziv_predmeta, espb)"
                + " VALUES ('"+brindeksa+"','"+sifrapredmeta+"','"+nazivpredmeta+"','"+espb+"') ";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Ispisuje ocene
     * @return
     */
    public ArrayList<GotoviIspitiDTO> prijaveIspisOcene()
    {
        sql = "SELECT * FROM prijave";

        ResultSet rs =null;

        ArrayList<GotoviIspitiDTO> listaPrijava = new ArrayList<GotoviIspitiDTO>();

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String idprijave = rs.getString("id_prijave");
                String sifrapredmeta = rs.getString("sifra_predmeta");
                String brindeksa = rs.getString("broj_indeksa");
                int espb = rs.getInt("espb");

                GotoviIspitiDTO p = new GotoviIspitiDTO(idprijave,espb,sifrapredmeta,brindeksa);

                listaPrijava.add(p);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaPrijava;

    }

    /**
     * dodeljivanje ocene studentu za odedjeni predmet
     * @param idprijave
     * @param ocena
     */
    public void dodeliOcenuZaIspit(String idprijave,int ocena)
    {
        sql = "SELECT * FROM prijave WHERE id_prijave='" + idprijave+"'";

        String sifrapredmeta = "Greska";
        String brindeksa = "Geska";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
            {
                 sifrapredmeta = rs.getString("sifra_predmeta");
                 brindeksa = rs.getString("broj_indeksa");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        sql = "INSERT INTO ocene "
                + "(broj_indeksa, sifra_predmeta, ocena)"
                + " VALUES ('"+brindeksa+"','"+sifrapredmeta+"','"+ocena+"') ";

        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        // brise ispit (prijavu) kojoj smo dodelili ocenu

        sql = "DELETE FROM prijave "
                + "WHERE id_prijave= '"+  idprijave  +"'";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }

    /** Ispisuje ispite studenta
     * @param brindeksa
     * @return
     */
    public ArrayList<IspitStudenta> ispisiIspiteStudenta(String brindeksa)
    {
        sql = "SELECT * FROM ocene WHERE broj_indeksa='" + brindeksa+"'";

        ArrayList<OcenaIzvestaj> izvestaj = new ArrayList<OcenaIzvestaj>();

        ArrayList<IspitStudenta> ispit = new ArrayList<IspitStudenta>();

        ResultSet rs =null;

        String sifrapredmeta = "greska";

        try{
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                sifrapredmeta = rs.getString("sifra_predmeta");
                int ocena = rs.getInt("ocena");

                OcenaIzvestaj iz = new OcenaIzvestaj(sifrapredmeta,ocena);

                izvestaj.add(iz);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////

        String nazivpredmeta = "Predmet nije na spisku";
        int espb = 0;

        for(int i=0;i<izvestaj.size();i++)
        {
            sql = "SELECT * FROM predmeti WHERE sifra_predmeta='"+ izvestaj.get(i).getSifrapredmeta() +"'";

            try{
                rs = stmt.executeQuery(sql);

                if(rs.first())
                {
                    nazivpredmeta = rs.getString("naziv_predmeta");
                    espb = rs.getInt("espb");

                    IspitStudenta is = new IspitStudenta(izvestaj.get(i).getSifrapredmeta(),nazivpredmeta,espb,izvestaj.get(i).getOcena());

                    ispit.add(is);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return ispit;
    }

    public String unosPredmeta(String sifrapredmeta,String nazivpredmeta,String idpredavaca,int espb)
    {
        if(espb == 0)
            return "ne";

        sql = "INSERT INTO predmeti "
                + "(sifra_predmeta, naziv_predmeta, espb, id_predavaca)"
                + " VALUES ('"+sifrapredmeta+"','"+nazivpredmeta+"','"+espb+"','"+idpredavaca+"') ";


        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return "da";
    }

    public String updateProfesora(String idpredavaca,String ime,String prezime,String username,String password)
    {
        String nadjen="da";

        String usernameStudenta = "";
        String sifra = "sifra";

        sql = "SELECT * FROM predavaci WHERE id_predavaca='" + idpredavaca+"'";

        ResultSet rs =null;

        try{
            rs = stmt.executeQuery(sql);

            if(rs.first())
            {
                usernameStudenta = rs.getString("username");
            }
            else
                return "ne";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sql = "UPDATE predavaci SET ime='" +ime +"',prezime='" + prezime + "',username = '" + username + "' WHERE id_predavaca='"+ idpredavaca +"'";

        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        String email = "profesor@gmail.com";
        String role = "PROFESOR";

        sql = "UPDATE users SET username='" +username +"',password='" + sifra + "',email = '" + email+ "', role = '" + role  + "',ime = '"+ime + "',prezime ='"+ prezime + "' WHERE username='"+ usernameStudenta +"'";

        try {
            stmt.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return nadjen;

    }

}
