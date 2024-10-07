/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.coordinator;

import forms.FrmPodesavanja;
import forms.MainForm;
import forms.controller.MainController;
import forms.controller.SettingsController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Settings;

/**
 *
 * @author Korisnik
 */
public class MainCoordinator {
    
    //singleton
    private static MainCoordinator instance;
    private final MainController mainController;
    //private int max_br_kl;

    private MainCoordinator() {
        mainController = new MainController(new MainForm());//poziva se samo main forma a setting forma se preko main forme poziva
    }

    public static MainCoordinator getInstance() {
        if(instance == null){
            instance = new MainCoordinator();
        }
        return instance;
    }

    public void openMainForm() {
        mainController.openForm();//ova metoda je kreirana u kontroleru i poziva .setvisible true
    }

    public void openSettingsForm(){
        SettingsController settingsController = new SettingsController(new FrmPodesavanja(mainController.getMainForm(), true));
        settingsController.openForm();
    }
    
    //kreiranje config fajla
    public void createConfigFile(){
        Settings.getInstance();
         
//        try {
//            Properties properties = new Properties();
//            properties.load(new FileInputStream("config/dbconfig.properties"));
//            max_br_kl = Integer.parseInt(properties.getProperty("max_br_kl"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MainCoordinator.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MainCoordinator.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public MainController getMainController() {
        return mainController;
    }

    
}
