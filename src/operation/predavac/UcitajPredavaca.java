/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.predavac;

import domain.GenericEntity;
import domain.Predavac;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajPredavaca extends AbstractGenericOperation {

    GenericEntity genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Predavac)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Predavac predavac = (Predavac) param;
        genericEntity = repository.nadji(predavac);
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }
    
    
}
