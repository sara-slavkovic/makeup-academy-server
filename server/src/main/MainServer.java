/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import forms.coordinator.MainCoordinator;

/**
 *
 * @author Korisnik
 */
public class MainServer {
    public static void main(String[] args) {
        //ovde se pokrece main coordinator
        //main coordinator u sebi ima polje main controller i inicijalizuje ga i salje mu parametar new mainform()
        MainCoordinator.getInstance().openMainForm();
    }
    
}
