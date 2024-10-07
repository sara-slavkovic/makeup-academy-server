/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.korisnik;

import domain.GenericEntity;
import domain.Korisnik;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UlogujKorisnika extends AbstractGenericOperation {

    GenericEntity genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception{
        //ovaj param je objekat Korisnik
        if(param == null || !(param instanceof Korisnik)){
            throw new Exception("Ivalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception{
        Korisnik k = (Korisnik) param;
        this.genericEntity = repository.uloguj(k, k.getKorisnickoIme(), k.getLozinka());
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }
    
   
}
