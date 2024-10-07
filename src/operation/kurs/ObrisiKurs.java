/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.kurs;

import domain.Kurs;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class ObrisiKurs extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Kurs)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.obrisi((Kurs)param);
    }
    
}
