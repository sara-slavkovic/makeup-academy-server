/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author Korisnik
 */
public interface DbRepository<T> extends Repository<T> {
    //nasledjuje interfejs repository
    //ali ne mora da overrideuje metode interfejs koji je nasledio drugi interfejs, nece overrajdovati metodu login
    
    default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();//ovo je bukv kao ono konekcija.getinst.getconnection
    }

    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();//kao konekcija.getinst.getconnection.commit
    }

    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();//kao konekcija.getinst.getconn.rollback
    }

    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
}
