/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import forms.FrmPodesavanja;
import forms.coordinator.MainCoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import server.Settings;

/**
 *
 * @author Korisnik
 */
public class SettingsController {

    private final FrmPodesavanja frmSettings;

    public SettingsController(FrmPodesavanja frmSettings) {
        this.frmSettings = frmSettings;
        addActionListener();
    }
    
    public void openForm() {
        frmSettings.setLocationRelativeTo(MainCoordinator.getInstance().getMainController().getMainForm());
        frmSettings.setTitle("Podešavanja");
        frmSettings.setVisible(true);
    }

    private void addActionListener() {
        frmSettings.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = frmSettings.getTxtURL().getText().trim();
                String username = frmSettings.getTxtUsername().getText().trim();
                String password = String.valueOf(frmSettings.getTxtPassword().getPassword()).trim();
                
                if(url.isEmpty() || username.isEmpty()){
                    JOptionPane.showMessageDialog(frmSettings, "URL i username moraju biti uneti");
                    return;
                }
                
                if(!url.contains("jdbc:mysql://")){
                    JOptionPane.showMessageDialog(frmSettings, "URL nije lepo unet. \nPrefiks koji ide pre seme je: jdbc:mysql:// \nNakon // upišite host adresu, zatim stavite : upišite broj porta, stavite / i onda upišite ime baze ");
                    return;
                }
                
                if(!url.contains("jdbc:mysql://localhost:3306/")){
                    JOptionPane.showMessageDialog(frmSettings, "URL nije lepo unet. \nPrefiks koji ide pre seme je: jdbc:mysql://localhost: \nNakon : upišite broj porta, stavite / i onda upišite ime baze ");
                    return;
                }
                
                //podesavanje properties fajla
                Settings.getInstance().setURL(url);
                Settings.getInstance().setUsername(username);
                Settings.getInstance().setPassword(password);
                Settings.getInstance().sacuvaj();//ovo je ono store i file output stream
                JOptionPane.showMessageDialog(frmSettings, "Izmene su uspesno unete u properties fajl");
                
                frmSettings.dispose();
            }
        });
    }
    
}
