package com.example.proba1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class Cont {

    Logged log;  // ulogovani korisnik ( id, pass, username, role )

    User user; // user koji pokusa da se loguje

    Data dt; // veza sa bazom

    User userr; // student

    AdminUser admin; // admin

    Profesor profesor; //  profesor

    String IDprijave = "";   // id prijave ispita

    String idProfesora = "";

    // akcija : logovanje
    @PostMapping("/checker")
    public String login(@ModelAttribute LoginDTO loginDTO, BindingResult errors, Model model){

        // uzimamo username i password iz template-a
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        dt = new Data(); // povezujemo se sa bazom

        user = dt.usFun(username,password);

        if (user != null) {

            log = new Logged(user.getId(),user.getUsername(),user.getRole());

            if (user.getRole().equals("ADMIN")) {

                admin = dt.vratiAdmin(log.getUsername());

                return "redirect:/admin";

            } else if (user.getRole().equals("STUDENT")) {

                userr = dt.vratiUser(log.getUsername());

                return "redirect:/index";

            } else {

                profesor = dt.vratiProfesora(log.getUsername());

                return "redirect:/profesor";
            }

        }
        model.addAttribute("check", true);
        return "indexlogin";
    }

    /**
     * Promena lozinke   (profesor) (student)
     * @param forgottenPassDTO username
     * @param errors
     * @param model
     * @return stranica studenta
     */

    // akcija : salje svoj username u bazu
    @PostMapping("/lozinka")
    public String lozinka(@ModelAttribute ForgottenPassDTO forgottenPassDTO,BindingResult errors,Model model)
    {
        String username = forgottenPassDTO.getUsername();

        //System.out.println(username);

        ForgottenPassDTO p = dt.posaljiZahtev(username);

        return "redirect:/index";
    }

    @GetMapping("/forgottenpassword")
    public String funkcija11(Model model)
    {
        model.addAttribute("forgottenPassDTO", new ForgottenPassDTO());

        return "forgottenpassword";
    }

    /**
     * Dodavanje profesora u bazu, (admin)
     * @param profesorDTO idpredavaca ime prezime username password
     * @param errors
     * @param model
     * @return ostaje na istoj stranici
     */
/*
    @PostMapping("/dodajprof")
    public String dodajProf(@ModelAttribute ProfesorDTO profesorDTO,BindingResult errors,Model model)
    {
        String idpredavaca = profesorDTO.getIdpredavaca();
        String ime = profesorDTO.getIme();
        String prezime = profesorDTO.getPrezime();
        String username = profesorDTO.getUsername();
        String password = profesorDTO.getPassword();
        //System.out.println("SIFRA: "+password);
        String nadjen = dt.unesiProfesora(ime,prezime,idpredavaca,username,password);

        if(nadjen.equals("ne"))
        {
            return "redirect:/dodajprofesore"; // dodavanje profesora
        }

        return "redirect:/dodajprofesorene"; // zabranjeno dodavanje
    }

    @GetMapping("/dodajprofesore")
    public String dodajProfesore1(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", false);

        return "dodajprofesore";
    }
    @GetMapping("/dodajprofesorene")
    public String dodajProfesorene(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", true);

        return "dodajprofesore";
    }
*/

    /**
     * Dodavanje profesora u bazu, dodavanje predmeta u bazu (admin)
     * @param profesorDTO idpredavaca ime prezime username password
     * @param errors
     * @param model
     * @return ostaje na istoj stranici
     */

    @PostMapping("/dodajprof")
    public String dodajProf(@ModelAttribute ProfesorDTO profesorDTO,BindingResult errors,Model model)
    {
        String idpredavaca = profesorDTO.getIdpredavaca();
        String ime = profesorDTO.getIme();
        String prezime = profesorDTO.getPrezime();
        String username = profesorDTO.getUsername();
        String password = profesorDTO.getPassword();
        //System.out.println("SIFRA: "+password);

        idProfesora = idpredavaca;

        String nadjen = dt.unesiProfesora(ime,prezime,idpredavaca,username,password);

        if(nadjen.equals("ne")) // ako prof. nije nadjen, on se dodaje i ide na
        {                                       //   dodajpredmete
            return "redirect:/dodajpredmete";
        }

        return "redirect:/dodajprofesorene";
    }

    @PostMapping("/dodelapredmeta")
    public String dodelaPredmeta(@ModelAttribute PredmetiPrijavaDTO predmetiPrijavaDTO,BindingResult errors,Model model)
    {
        String sifrapredmeta = predmetiPrijavaDTO.getSifrapredmeta();
        String nazivpredmeta = predmetiPrijavaDTO.getNazivpredmeta();
        int espb = predmetiPrijavaDTO.getEspb();
        //String idpredavaca = predmetiPrijavaDTO.getIdpredavaca();

        String uneto = dt.unosPredmeta(sifrapredmeta,nazivpredmeta,idProfesora,espb);

        if(uneto.equals("da"))
        {
            return "redirect:/dodajpredmete";
        }
        return "redirect:/dodajprofesore";
    }

    @GetMapping("/dodajpredmete")
    public String dodajP(Model model)
    {
        model.addAttribute("predmetiPrijavaDTO",new PredmetiPrijavaDTO());

        return "dodajpredmete";
    }

    @GetMapping("/dodajprofesore")
    public String dodajProfesore1(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", false);

        return "dodajprofesore";
    }

    @GetMapping("/dodajprofesorene")
    public String dodajProfesorene(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", true);

        return "dodajprofesore";
    }

    /***
     * Update profesora
     */

    @GetMapping("/updateprofesora")
    public String updateprofesora(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", false);

        return "updateprofesora";
    }

    @GetMapping("/updateprofesorane")
    public String updateprofesoraNe(Model model)
    {
        model.addAttribute("profesorDTO",new ProfesorDTO());
        model.addAttribute("check", true);

        return "updateprofesora";
    }

    @PostMapping("/updatepredavaca")
    public String updatePredavaca(@ModelAttribute ProfesorDTO profesorDTO, BindingResult errors,Model model)
    {
        String idpredavaca = profesorDTO.getIdpredavaca();
        String ime = profesorDTO.getIme();
        String prezime = profesorDTO.getPrezime();
        String username = profesorDTO.getUsername();
        String password = profesorDTO.getPassword();

        String nadjen = dt.updateProfesora(idpredavaca,ime,prezime,username,password);

        if(nadjen.equals("da"))
        {
            return "redirect:/updateprofesora";
        }
        return "redirect:/updateprofesorane";
    }

    /**
     * Dodavanje studenata u bazu (admin)
     * @param studentDTO student
     * @param errors
     * @param model
     * @return  ostaje na istoj stranici
     */

    @PostMapping("/registracijastudenta")
    public String dodavanjeStudentaUBazu(@ModelAttribute StudentDTO studentDTO,BindingResult errors,Model model)
    {
        String brindeksa = studentDTO.getBrindeksa();
        String ime = studentDTO.getIme();
        String prezime = studentDTO.getPrezime();
        String mobilni = studentDTO.getMobilni();
        String fiksni = studentDTO.getFiksni();
        String jmbg = studentDTO.getJmbg();
        String email = studentDTO.getEmail();
        float prosek = studentDTO.getProsek();
        float finansije = studentDTO.getFinansije();
        int espb = studentDTO.getEspb();
        String username = studentDTO.getUsername();
        String password = studentDTO.getPassword();
        String role = studentDTO.getRole();
        role = "STUDENT";

        String nadjen = dt.dodajStudenta(ime,prezime,brindeksa,jmbg,prosek,finansije,espb,email,role,fiksni,mobilni,username,password);

        if(nadjen.equals("ne"))
        {
            return  "redirect:/indeksregister";
        }

        return "redirect:/indeksregisterne";


    }
    @GetMapping("/indeksregister")
    public String indexreg(Model model)
    {
        model.addAttribute("studentDTO",new StudentDTO());
        model.addAttribute("check", false);

        return "indeksregister";
    }
    @GetMapping("/indeksregisterne")
    public String indexregne(Model model)
    {
        model.addAttribute("studentDTO",new StudentDTO());
        model.addAttribute("check", true);

        return "indeksregister";
    }

    /**
     * Brise studenta ili profesora
     * @param userDeleteDTO
     * @param errors
     * @param model
     * @return
     */

    @PostMapping("/brisanjepodatka")
    public String brisanjeUser(@ModelAttribute UserDeleteDTO userDeleteDTO,BindingResult errors,Model model)
    {
        String username = userDeleteDTO.getUsername();
        String role = userDeleteDTO.getRole();

        //System.out.println(username+" "+role);

        String nadjen = dt.brisanjeKorisnika(username,role);

        if(nadjen.equals("ne"))
        {
            return   "redirect:/brisanjee"; // nije dozvoljeno brisanje
        }

        return "redirect:/brisanje"; // dozvoljeno brisanje
    }

    @GetMapping("/brisanje")
    public String brisanje1(Model model)
    {
        model.addAttribute("userDeleteDTO", new UserDeleteDTO());
        model.addAttribute("check", false);

        return "/brisanje";
    }
    @GetMapping("/brisanjee")
    public String brisanje11(Model model)
    {
        model.addAttribute("userDeleteDTO", new UserDeleteDTO());
        model.addAttribute("check", true);

        return "/brisanje";
    }

    /**
     * Ispis profesora
     * @param model
     * @return
     */

    @GetMapping("/ispisiprofesore")
    public String funnnnnnnnn(Model model)
    {
        ArrayList<ProfesorDTO> listaProfesora = dt.profesoriJson();

        model.addAttribute("listaProfesora",listaProfesora);

        return "prikaziprofesore";
    }

    /**
     * Ispisuje prijave ispita  (profesor)
     * @param model
     * @return
     */

    @GetMapping("/ispisiprijave")
    public String ispisiPrijaveProf(Model model)
    {
        ArrayList<PrijaveDTO> listaPrijava = dt.prijaveIspis();

        model.addAttribute("listaPrijava",listaPrijava);

        return "prikazprijava";
    }

    /**
     * Prijava ispita (student)
     * @param model
     * @return
     */

    // ispisuje listu predmeta za prijavu
    @GetMapping("/eprijava")
    public String ispispredmetazaprijavu(Model model)
    {
        ArrayList<PredmetiPrijavaDTO> listaPredmeta = dt.predmetizaprijavu();

        model.addAttribute("listaPredmeta",listaPredmeta);
        model.addAttribute("ime",userr.getIme());
        model.addAttribute("prezime",userr.getPrezime());

        return "predmetiprijava";
    }

    // uzima sifru predmeta koga smo odabrali klikom na dugme
    @PostMapping("/prijaviispit")
    public String prijaviIspitFun(@RequestParam(value="sifrapredmeta") String sifrapredmeta,Model model)
    {
        //System.out.println("Sifra predmeta: " + sifrapredmeta);

        dt.prijavljujemIspit(userr.getBrindeksa(),sifrapredmeta);

        return "redirect:/eprijava";
    }

    /** Dodeljivanje ocena (profesor)
     * @param model
     * @return
     */

    @GetMapping("/dajocenu")
    public String dajOcenu(Model model)
    {
        ArrayList<GotoviIspitiDTO> listaPrijava = dt.prijaveIspisOcene();

        model.addAttribute("listaPrijava",listaPrijava);

        return "dodeliocenu";
    }

    @PostMapping("/dodeliocenu")
    public String dodeliOcenuFun(@RequestParam(value = "idprijave") String idprijave)
    {
        IDprijave = idprijave;

        return "redirect:/ocena";
    }

    @GetMapping("/ocena")
    public String dajOcenuu(Model model)
    {
        model.addAttribute("ocenaDTO", new OcenaDTO());

        return "ocena";
    }

    @PostMapping("/potvrdiocenu")
    public String potvrdiOcenu(@ModelAttribute OcenaDTO ocenaDTO,BindingResult errors,Model model)
    {
        int ocena = ocenaDTO.getOcena();

        dt.dodeliOcenuZaIspit(IDprijave,ocena);

        return "redirect:/dajocenu"; // izmeni kasnije
    }

    /**
     * Strana za logovanje
     * @param model
     * @return
     */

    @GetMapping("/indexlogin")
    public String logovanje1(Model model)
    {
        model.addAttribute("loginDTO", new LoginDTO());
        model.addAttribute("check", false);

        return "indexlogin";
    }

    // logout
    @GetMapping("/logout")
    public String logout() {

        log = null;

        userr = null;

        admin = null;

        profesor = null;

        return "redirect:/indexlogin";
    }

    /**
     * Pocetna strana profesora
     * @param model
     * @return
     */
    @GetMapping("/profesor")
    public String profesorStrana(Model model)
    {
        model.addAttribute("ime",profesor.getIme());
        model.addAttribute("prezime",profesor.getPrezime());
        model.addAttribute("idpredavaca","ID profesora:   " + profesor.getId_predavaca());

        return "profesor";
    }

    /**
     * Pocetna strana admina
     * @param model
     * @return
     */

    @GetMapping("/admin")
    public String adminStrana(Model model)
    {
        model.addAttribute("ime",admin.getIme());
        model.addAttribute("prezime",admin.getPrezime());
        model.addAttribute("jmbg",admin.getJmbg());
        model.addAttribute("mobilni",admin.getMobilni());
        model.addAttribute("fiksni",admin.getFiksni());

        return "admin";
    }

    @GetMapping("/predmeti")
    public String predmetiFun(Model model)
    {
        ArrayList<PredmetiStudentaDTO> predmetiStudenta = dt.predmetiStudenta();

        model.addAttribute("predmetiStudenta",predmetiStudenta);
        model.addAttribute("ime",userr.getIme());
        model.addAttribute("prezime",userr.getPrezime());

        return "predmetistudenta";
    }

    @GetMapping("/finansije")
    public String finansije(Model model)
    {
        model.addAttribute("ime",userr.getIme());
        model.addAttribute("prezime",userr.getPrezime());
        model.addAttribute("finansije",userr.getFinansije());

        return "finansije";
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        model.addAttribute("imeosobe",userr.getIme());
        model.addAttribute("prezimeosobe",userr.getPrezime());
        model.addAttribute("index","Broj indeksa: " + userr.getBrindeksa());
        model.addAttribute("jmbg","JBMG: " + userr.getJmbg());
        model.addAttribute("email","Email: "+userr.getEmail());
        model.addAttribute("mobilni","Mobilni telefon: "+ userr.getMobilni());
        model.addAttribute("fiksni","Fiksni: " + userr.getFiksni() );
        model.addAttribute("prosek",userr.getProsek());
        model.addAttribute("espb",  userr.getEspb());

        return "index";
    }

    // ispisuje ispite koji su ocenjeni
    @GetMapping("/ispiti")
    public String ispiti(Model model)
    {
        ArrayList<IspitStudenta> ispitStudenta =  dt.ispisiIspiteStudenta(userr.getBrindeksa());

        model.addAttribute("ispitiStudenta",ispitStudenta);
        model.addAttribute("ime",userr.getIme());
        model.addAttribute("prezime",userr.getPrezime());

        return "ispitizapis";
    }

}
