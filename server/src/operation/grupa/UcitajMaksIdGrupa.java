/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.grupa;

import domain.Grupa;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajMaksIdGrupa extends AbstractGenericOperation {

    private int id;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Grupa)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        id = repository.vratiMaksId((Grupa) param);//mora da se posalje grupa kao objekat da bi se znalo koji gettablename i sl
    }

    public int getId() {
        return id;
    }

}
