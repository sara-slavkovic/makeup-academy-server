/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
/**
 *
 * @author Korisnik
 */
public class DbConnectionFactory {
    // klasa konekcija
    //singleton
    private static DbConnectionFactory instance;
    private Connection connection;

    private DbConnectionFactory(){
    }

    public static DbConnectionFactory getInstance() {
        if(instance == null){
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws Exception{
        if(connection == null || connection.isClosed()){
            //prvo moramo da izvucemo iz config fajla url username i password
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }

    
    
    
}
