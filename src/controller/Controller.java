/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.GenericEntity;
import domain.Grupa;
import domain.Korisnik;
import domain.Kurs;
import domain.Predavac;
import domain.Prijava;
import domain.RasporedKursa;
import domain.TipSminkanja;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.grupa.KreirajGrupu;
import operation.grupa.NadjiGrupe;
import operation.grupa.UcitajGrupu;
import operation.grupa.UcitajListuGrupa;
import operation.grupa.UcitajMaksIdGrupa;
import operation.korisnik.UcitajKorisnika;
import operation.korisnik.UlogujKorisnika;
import operation.korisnik.ZapamtiKorisnika;
import operation.kurs.PromeniKurs;
import operation.kurs.KreirajKurs;
import operation.kurs.NadjiKurseve;
import operation.kurs.ObrisiKurs;
import operation.kurs.UcitajKurs;
import operation.kurs.UcitajListuKurseva;
import operation.predavac.UcitajListuPredavaca;
import operation.predavac.UcitajPredavaca;
import operation.prijava.IzmeniPrijavu;
import operation.prijava.KreirajPrijavu;
import operation.prijava.UcitajListuPrijava;
import operation.rasporedKursa.UcitajListuRasporeda;
import operation.tipsminkanja.UcitajListuTipovaSminkanja;
import operation.tipsminkanja.UcitajTipSminkanja;
import server.Server;

/**
 *
 * @author Korisnik
 */
public class Controller {

    private static Controller instance;
    //private 
    private Server server;
    private List<GenericEntity> prijavljeniKorisnici;//lista ulogovanih korisnika

    private Controller() {
        this.prijavljeniKorisnici = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void startujServer() {//pokretanje servera, preko main controllera
        if (server == null || !server.isAlive()) {
            server = new Server();//ovde se inicijalizuje serverskisoket na 9000 i lista niti klijenata
            server.start();
            System.out.println("Server je pokrenut");
        }
    }

    public void stopServer() {
        System.out.println("Zaustavljen server");
        server.kraj();//ovde je kraj = true; i serverskisoket.close()
    }

    public List<GenericEntity> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public void setPrijavljeniKorisnici(List<GenericEntity> prijavljeniKorisnici) {
        this.prijavljeniKorisnici = prijavljeniKorisnici;
    }

    ////////////////////////////////////////////////////////////
    // HENDLOVANJE OPERACIJA
    ////////////////////////////////////////////////////////////
    public GenericEntity login(String korisnickoIme, String lozinka) throws Exception {
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(korisnickoIme);
        k.setLozinka(lozinka);

        AbstractGenericOperation operation = new UlogujKorisnika();
        operation.execute(k);
        return ((UlogujKorisnika) operation).getGenericEntity();//uloguj korisnika nasledjuje abstract gen op pa mozee da se kastuje
    }

    public void sacuvajKorisnika(Korisnik kor) throws Exception {
        AbstractGenericOperation operation = new ZapamtiKorisnika();
        operation.execute(kor);
    }

    public List<GenericEntity> vratiSveKurseve() throws Exception {
        AbstractGenericOperation operation = new UcitajListuKurseva();
        operation.execute(new Kurs());//salje se new kurs da bi se poslao neki parametar jer u precondition proverava da li je param
        //instanceof kurs i mora da se zadovolji taj uslov
        List<GenericEntity> kursevi = ((UcitajListuKurseva) operation).getList();//ovo je ono dbb pa kad vrati dobija se lista u ucitajlistikurseva
        //namestanje tipa sminkanja i predavaca
        for (GenericEntity genericEntity : kursevi) {
            Kurs k = (Kurs) genericEntity;
            k.setTipSminkanja((TipSminkanja) dajTipSminkanja(k.getTipSminkanja().getIdTipaSminkanja()));
            k.setPredavac((Predavac) dajPredavaca(k.getPredavac().getIdPredavaca()));
        }
        return kursevi;
    }

    public GenericEntity dajTipSminkanja(int id) throws Exception {
        TipSminkanja tipSminkanja = new TipSminkanja();
        tipSminkanja.setIdTipaSminkanja(id);

        AbstractGenericOperation operation = new UcitajTipSminkanja();
        operation.execute(tipSminkanja);
        return ((UcitajTipSminkanja) operation).getGenericEntity();
    }

    public GenericEntity dajPredavaca(int id) throws Exception {
        Predavac predavac = new Predavac();
        predavac.setIdPredavaca(id);

        AbstractGenericOperation operation = new UcitajPredavaca();
        operation.execute(predavac);
        return ((UcitajPredavaca) operation).getGenericEntity();
    }

    public void sacuvajKurs(Kurs kurs) throws Exception {
        AbstractGenericOperation operation = new KreirajKurs();
        operation.execute(kurs);
    }

    public List<TipSminkanja> vratiSveTipoveSminkanja() throws Exception {
        AbstractGenericOperation operation = new UcitajListuTipovaSminkanja();
        operation.execute(new TipSminkanja());
        List<TipSminkanja> tipoviSminkanja = ((UcitajListuTipovaSminkanja) operation).getList();
        return tipoviSminkanja;
    }

    public List<Predavac> vratiSvePredavace() throws Exception {
        AbstractGenericOperation operation = new UcitajListuPredavaca();
        operation.execute(new Predavac());
        List<Predavac> predavaci = ((UcitajListuPredavaca) operation).getList();
        return predavaci;
    }

    public void obrisiKurs(Kurs kursZaBrisanje) throws Exception {
        AbstractGenericOperation operation = new ObrisiKurs();
        operation.execute(kursZaBrisanje);
    }

    public List<GenericEntity> filterKurseve(String nazivIzPretrage) throws Exception {
        Kurs kurs = new Kurs();
        kurs.setNazivKursa(nazivIzPretrage);

        AbstractGenericOperation operation = new NadjiKurseve();
        operation.execute(kurs);

        List<GenericEntity> kursevi = ((NadjiKurseve) operation).getList();
        for (GenericEntity entity : kursevi) {
            Kurs k = (Kurs) entity;
            k.setTipSminkanja((TipSminkanja) dajTipSminkanja(k.getTipSminkanja().getIdTipaSminkanja()));
            k.setPredavac((Predavac) dajPredavaca(k.getPredavac().getIdPredavaca()));
        }
        //return ((NadjiKurseve) operation).getList();//ne moze tako,mora posebno da se postavi tip simnkanja i predavac
        return kursevi;
    }

    public void promeniKurs(Kurs kursZaIzmenu) throws Exception {
        AbstractGenericOperation operation = new PromeniKurs();
        operation.execute(kursZaIzmenu);
    }

    public List<Grupa> vratiSveGrupe() throws Exception {
        AbstractGenericOperation operation = new UcitajListuGrupa();
        operation.execute(new Grupa());

        List<Grupa> grupe = ((UcitajListuGrupa) operation).getGrupe();
        //mora da se namesti kurs
        for (Grupa g : grupe) {
            if (g.getKurs() == null) {
                return null;//sve grupe moraju da imaju kurs
            }
            g.setKurs((Kurs) dajKurs(g.getKurs().getIdKursa()));
        }
        return grupe;
    }

    public GenericEntity dajKurs(int id) throws Exception {
        Kurs kurs = new Kurs();
        kurs.setIdKursa(id);

        AbstractGenericOperation operation = new UcitajKurs();
        operation.execute(kurs);
        return ((UcitajKurs) operation).getGenericEntity();
    }

    public int vratiMaksIdGrupa() throws Exception {
        AbstractGenericOperation operation = new UcitajMaksIdGrupa();
        operation.execute(new Grupa());
        return ((UcitajMaksIdGrupa) operation).getId();
    }

    public GenericEntity dajGrupu(int idGrupe) throws Exception {
        Grupa g = new Grupa();
        g.setIdGrupe(idGrupe);

        AbstractGenericOperation operation = new UcitajGrupu();
        operation.execute(g);
        return ((UcitajGrupu) operation).getGenericEntity();
    }

    public void sacuvajPrijavu(Prijava prijava) throws Exception {
        AbstractGenericOperation operation = new KreirajPrijavu();
        operation.execute(prijava);
    }

    public List<GenericEntity> filterGrupe(String nazivGrupe) throws Exception {
        Grupa grupa = new Grupa();
        grupa.setNazivGrupe(nazivGrupe);

        AbstractGenericOperation operation = new NadjiGrupe();
        operation.execute(grupa);

        List<GenericEntity> grupe = ((NadjiGrupe) operation).getList();
        for (GenericEntity genericEntity : grupe) {
            Grupa g = (Grupa) genericEntity;

            g.setKurs((Kurs) dajKurs(g.getKurs().getIdKursa()));
        }
        return grupe;
    }

    public List<RasporedKursa> vratiSveRasporede() throws Exception {
        AbstractGenericOperation operation = new UcitajListuRasporeda();
        operation.execute(new RasporedKursa());

        List<RasporedKursa> rasporedi = ((UcitajListuRasporeda) operation).getRasporedi();
        //namestanje grupe 
        for (RasporedKursa r : rasporedi) {
            r.setGrupa((Grupa) dajGrupu(r.getGrupa().getIdGrupe()));
        }

        return rasporedi;
    }

    public void sacuvajGrupu(Grupa g) throws Exception {
        AbstractGenericOperation operation = new KreirajGrupu();
        operation.execute(g);
    }

    public List<GenericEntity> vratiSvePrijave() throws Exception {
        AbstractGenericOperation operation = new UcitajListuPrijava();
        operation.execute(new Prijava());

        List<GenericEntity> prijave = ((UcitajListuPrijava) operation).getList();
        //namestanje korisnika,kursa,grupe
        for (GenericEntity entity : prijave) {
            Prijava p = (Prijava) entity;
            p.setKorisnik((Korisnik) dajKorisnika(p.getKorisnik().getIdKorisnika()));
            p.setKurs((Kurs) dajKurs(p.getKurs().getIdKursa()));
            p.setGrupa((Grupa) dajGrupu(p.getGrupa().getIdGrupe()));
        }
        return prijave;
    }

    public GenericEntity dajKorisnika(int id) throws Exception {
        Korisnik k = new Korisnik();
        k.setIdKorisnika(id);

        AbstractGenericOperation operation = new UcitajKorisnika();
        operation.execute(k);
        return ((UcitajKorisnika) operation).getGenericEntity();
    }

    public void promeniPrijavu(Prijava prijavaZaIzmenu) throws Exception {
        AbstractGenericOperation operation = new IzmeniPrijavu();
        operation.execute(prijavaZaIzmenu);
    }



}
