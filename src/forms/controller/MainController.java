/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import controller.Controller;
import java.awt.event.ActionListener;
import forms.MainForm;
import forms.coordinator.MainCoordinator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Korisnik
 */
public class MainController {
    
    private final MainForm frmMain;

    public MainController(MainForm frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }
    
    public void openForm(){
        frmMain.setTitle("Lora Beauty");
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiPodesavanjaKorisnikaAddActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openSettingsForm();
            }
        });
        
        frmMain.oSoftveruKorisnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frmMain, "Softver je napravila studentkinja Sara Slavković");
            }
        });
        
        frmMain.addStartBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                File file = new File("config/dbconfig.properties");
//                if(!file.exists()){
//                    //MainCoordinator.getInstance().openSettingsForm();
//                    MainCoordinator.getInstance().createConfigFile();
//                }
//                if(file.exists()){
//                   Controller.getInstance().startujServer();
//                   frmMain.getBtnStart().setEnabled(false);
//                   frmMain.getBtnStop().setEnabled(true);
//                   frmMain.getLblStatus().setText("Status: Server je pokrenut");
//                   frmMain.getLblStatus().setBackground(Color.GREEN);                   
//                }
                   Controller.getInstance().startujServer();
                   frmMain.getBtnStart().setEnabled(false);
                   frmMain.getBtnStop().setEnabled(true);
                   frmMain.getLblStatus().setText("Status: Server je pokrenut");
                   frmMain.getLblStatus().setBackground(Color.GREEN);                   
                
            }
        });
        
        frmMain.addStopBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().stopServer();
                frmMain.getBtnStop().setEnabled(false);
                frmMain.getBtnStart().setEnabled(true);
                frmMain.getLblStatus().setText("Status: Server je zaustavljen");
                frmMain.getLblStatus().setBackground(Color.RED);
            }
        });
        
        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                File file = new File("config/dbconfig.properties");
                if(!file.exists()){
                    //MainCoordinator.getInstance().openSettingsForm();
                    MainCoordinator.getInstance().createConfigFile();
                } 
            }           
        });
    }

    public MainForm getMainForm() {
        return frmMain;
    }
    
    
    
}
