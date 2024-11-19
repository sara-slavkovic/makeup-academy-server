/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.kurs;

import domain.GenericEntity;
import domain.Kurs;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajKurs extends AbstractGenericOperation {

    private GenericEntity genericEntity;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Kurs)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Kurs kurs = (Kurs) param;
        genericEntity = repository.nadji(kurs);
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }

}
