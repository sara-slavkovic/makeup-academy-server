/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.thread.ProcessClientRequests;

/**
 *
 * @author Korisnik
 */
public class Server extends Thread {
    
    private boolean kraj = false;
    private ServerSocket serverskiSoket;
    private List<ProcessClientRequests> klijenti;

    public Server() {
        //u konstruktoru inicijalizacija liste niti klijenata i serverskog soketa na port 9000
        klijenti = new LinkedList<>();
        try {
            serverskiSoket = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Override
    public void run() {
        while(!kraj){
            try {
                System.out.println("Cekanje na konekciju");
                Socket socket = serverskiSoket.accept();
                
                ProcessClientRequests klijent = new ProcessClientRequests(socket, this);
                System.out.println("Klijent se prikljucio");
                klijent.start();
                klijenti.add(klijent);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<ProcessClientRequests> getKlijenti() {
        return klijenti;
    }
    
    public void kraj(){// zaustavi server
        try {
            //moraju da se prekinu niti svih klijenata
            for (ProcessClientRequests processClientRequests : klijenti) {
                processClientRequests.kraj();
            }
            Controller.getInstance().setPrijavljeniKorisnici(new ArrayList<>());//da se ponovo inicijalizuje, tj da se obrisu svi
            serverskiSoket.close();
            kraj = true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
            
            
            
}
