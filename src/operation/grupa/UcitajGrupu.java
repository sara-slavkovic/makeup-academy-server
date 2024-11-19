/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.grupa;

import domain.GenericEntity;
import domain.Grupa;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajGrupu extends AbstractGenericOperation {

    private GenericEntity genericEntity; 
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Grupa)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Grupa grupa = (Grupa) param;
        genericEntity = repository.nadji(grupa);
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }
    
}
