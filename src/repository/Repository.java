/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domain.GenericEntity;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public interface Repository<T> {
    //interfejs u sebi ima samo potpise metoda bez bloka
    //telo metode se definise u klasama koje implementiraju ovaj interfejs
    //ovaj interfejs implementira repositorydngeneric klasa i tu se nalaze blokovi metoda-overrajdovana metoda
    
    //CRUD operacije
    public void dodaj(T param) throws Exception;
    public List<T> vratiSve(T param) throws Exception;
    public void promeni(T param) throws Exception;
    public void obrisi(T param) throws Exception;
    /////////////////////////////////////////////
    public GenericEntity uloguj(GenericEntity entity) throws Exception;
    public List<GenericEntity> nadjiPoKorisnickomImenuIMejlu(GenericEntity entity, String mejl, String korisnickoIme) throws Exception;
    public GenericEntity nadji(GenericEntity entity) throws Exception;
    public List<GenericEntity> filter(GenericEntity entity) throws Exception;
    public int vratiMaksId(GenericEntity entity) throws Exception;
    public void promeniPrijavu(GenericEntity entity) throws Exception;


}
