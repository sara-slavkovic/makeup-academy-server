/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.GenericEntity;
import domain.Grupa;
import domain.Korisnik;
import domain.Kurs;
import domain.Predavac;
import domain.Prijava;
import domain.RasporedKursa;
import domain.TipSminkanja;
import java.net.Socket;
import java.util.List;
import server.Server;

/**
 *
 * @author Korisnik
 */
public class ProcessClientRequests extends Thread {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    //private Server server;
    private boolean kraj = false;

    public ProcessClientRequests(Socket socket, Server server) {//obavezno parametarski konstr
        this.socket = socket;//ovo je onaj socket iz serverskisoket.accept
        //this.server = server;
        receiver = new Receiver(socket);//treba mu socket kao parametar jer poziva objectinputstream = new objinstr(socket.getinputstr)
        sender = new Sender(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            //dodajemo i ovde while petlju da mozemo da prekinemo niti klijenata iz klase server, tu ima
            //cela lista obradeklijentskihzahteva tj lista niti klijenata i onda ih u metodi kraj tj zaustaviserver prekidam svaku
            //nit po nit od svakog klijenta
            try {
                Request request = (Request) receiver.receive();//klijentskizahtev = primizahtev()
                Response response = new Response();//serverskiodg = new serverskiodg()
                response.setOperation(request.getOperation());//i u odgovoru imam sad operaciju,pa je setujem iz zahteva
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            Korisnik korisnik = (Korisnik) request.getArgument();//getparam i to saljem kao parametar kontroleru
                            // generic entity je odgovor od kontrolera
                            GenericEntity entity = Controller.getInstance().login(korisnik.getKorisnickoIme(), korisnik.getLozinka());
                            if (entity == null) {
                                Exception e = new Exception("Korisnik nema pravo pristupa sistemu");
                                throw e;
                            } else {
                                //ako nije prijavljen tj nema ga u listi prijavljenih
                                if (!Controller.getInstance().getPrijavljeniKorisnici().contains(entity)) {
                                    Controller.getInstance().getPrijavljeniKorisnici().add(entity);
                                    response.setUspesno(true);
                                    response.setPoruka("Uspešna prijava!");
                                    response.setResult(entity);
                                } else {
                                    //ako je vec prijavljen
                                    Exception e = new Exception("Korisnik je već prijavljen");
                                    response.setUspesno(false);
                                    response.setException(e);
                                }
                            }
                            break;

                        case KRAJ_RADA:
                            Korisnik k = (Korisnik) request.getArgument();
                            Controller.getInstance().getPrijavljeniKorisnici().remove(k);
                            kraj = true;
                            break;

                        case SACUVAJ_KORISNIKA:
                            Korisnik kor = (Korisnik) request.getArgument();
                            Controller.getInstance().sacuvajKorisnika(kor);
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_KURSEVE:
                            List<GenericEntity> kursevi = Controller.getInstance().vratiSveKurseve();
                            response.setResult(kursevi);
                            response.setUspesno(true);
                            break;

                        case SACUVAJ_KURS:
                            Kurs kurs = (Kurs) request.getArgument();
                            Controller.getInstance().sacuvajKurs(kurs);
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_TIPOVE_SMINKANJA:
                            List<TipSminkanja> tipoviSminkanja = Controller.getInstance().vratiSveTipoveSminkanja();
                            response.setResult(tipoviSminkanja);
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_PREDAVACE:
                            List<Predavac> predavaci = Controller.getInstance().vratiSvePredavace();
                            response.setResult(predavaci);
                            response.setUspesno(true);
                            break;

                        case OBRISI_KURS:
                            Kurs kursZaBrisanje = (Kurs) request.getArgument();
                            Controller.getInstance().obrisiKurs(kursZaBrisanje);
                            response.setUspesno(true);
                            break;

                        case VRATI_KURSEVE_NAZIV:
                            String nazivIzPretrage = (String) request.getArgument();
                            List<GenericEntity> kurseviPoNazivu = Controller.getInstance().filterKurseve(nazivIzPretrage);
                            response.setResult(kurseviPoNazivu);
                            response.setUspesno(true);
                            break;

                        case IZMENI_KURS:
                            Kurs kursZaIzmenu = (Kurs) request.getArgument();
                            Controller.getInstance().promeniKurs(kursZaIzmenu);
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_GRUPE:
                            List<Grupa> grupe = Controller.getInstance().vratiSveGrupe();
                            response.setResult(grupe);
                            response.setUspesno(true);
                            break;

                        case VRATI_MAKS_ID_GRUPA:
                            int maksIdGrupa = Controller.getInstance().vratiMaksIdGrupa();
                            response.setResult(maksIdGrupa);
                            response.setUspesno(true);
                            break;

                        case VRATI_GRUPU:
                            int idGrupe = (int) request.getArgument();
                            Grupa grupa = (Grupa) Controller.getInstance().dajGrupu(idGrupe);
                            response.setResult(grupa);
                            response.setUspesno(true);
                            break;

                        case SACUVAJ_PRIJAVU:
                            Prijava prijava = (Prijava) request.getArgument();
                            Controller.getInstance().sacuvajPrijavu(prijava);
                            response.setUspesno(true);
                            break;

                        case VRATI_GRUPE_NAZIV:
                            String nazivGrupe = (String) request.getArgument();
                            List<GenericEntity> grupePoNazivu = Controller.getInstance().filterGrupe(nazivGrupe);
                            response.setResult(grupePoNazivu);
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_RASPOREDE:
                            List<RasporedKursa> rasporedi = Controller.getInstance().vratiSveRasporede();
                            response.setResult(rasporedi);
                            response.setUspesno(true);
                            break;

                        case SACUVAJ_GRUPU:
                            Grupa g = (Grupa) request.getArgument();
                            Controller.getInstance().sacuvajGrupu(g);
                            System.out.println(g.toString());
                            response.setUspesno(true);
                            break;

                        case VRATI_SVE_PRIJAVE:
                            List<GenericEntity> prijave = Controller.getInstance().vratiSvePrijave();
                            response.setResult(prijave);
                            response.setUspesno(true);
                            break;

                        case IZMENI_PRIJAVU:
                            Prijava prijavaZaIzmenu = (Prijava) request.getArgument();
                            Controller.getInstance().promeniPrijavu(prijavaZaIzmenu);
                            response.setUspesno(true);
                            break;

                    }
                } catch (Exception e) {//ovo je poseban try catch za hendlovanje exceptiona
                    e.printStackTrace();
                    response.setException(e);
                    response.setUspesno(false);
                }
                sender.send(response);//posaljiodgovor(so)
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void kraj() {
        kraj = true;
    }

}
