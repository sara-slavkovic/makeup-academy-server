/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.tipsminkanja;

import domain.GenericEntity;
import domain.TipSminkanja;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajTipSminkanja extends AbstractGenericOperation {

    private GenericEntity genericEntity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof TipSminkanja)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        TipSminkanja ts = (TipSminkanja) param;
        genericEntity = repository.nadji(ts);
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }
    
    
}
