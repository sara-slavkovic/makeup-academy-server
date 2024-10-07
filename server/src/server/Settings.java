/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Settings {
    
    //singleton
    private static Settings instance;
    private Properties properties;
    private String putanja = "C:\\Users\\Korisnik\\Desktop\\pro soft projekat\\ServerSara\\config\\dbconfig.properties";

//    private Settings() {
//        properties = new Properties();
//        try {
//            properties.load(new FileInputStream("config/dbconfig.properties"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    private Settings() {
        properties = new Properties();
        File configFile = new File(putanja);
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            setURL("jdbc:mysql://localhost:3306/seminarski_softveri");
            setUsername("root");
            setPassword("");
            //setMaxBrKl("3");
            sacuvaj();            
        }else{
            try {
                properties.load(new FileInputStream(configFile));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Settings getInstance() {
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }
    
    public void setURL(String url){
        properties.setProperty("url", url);
    }
    
    public void setUsername(String username){
        properties.setProperty("username", username);
    }
    
    public void setPassword(String password){
        properties.setProperty("password", password);
    }
    
//    public void setMaxBrKl(String max){
//        properties.setProperty("max_br_kl", max);
//    }
    
    public void sacuvaj(){
        try {
            properties.store(new FileOutputStream(putanja), "Property file izmenjen");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
