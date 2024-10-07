/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.korisnik;

import domain.GenericEntity;
import domain.Korisnik;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class ZapamtiKorisnika extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Korisnik)){
            throw new Exception("Invalid parameter");
        }
        Korisnik k = (Korisnik) param;
        List<GenericEntity> lista = repository.nadjiPoKorisnickomImenuIMejlu(k,k.getMejl(), k.getKorisnickoIme());
        if(!lista.isEmpty()){
            throw new Exception("Korisnik sa ovim mejlom i/ili korisnickim imenom vec postoji u bazi");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Korisnik k = (Korisnik) param;
        repository.dodaj(k);
    }
    
}
