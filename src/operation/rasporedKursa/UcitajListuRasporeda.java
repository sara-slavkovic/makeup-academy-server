/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rasporedKursa;

import domain.RasporedKursa;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajListuRasporeda extends AbstractGenericOperation {

    List<RasporedKursa> rasporedi;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof RasporedKursa)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rasporedi = repository.vratiSve((RasporedKursa) param);
    }

    public List<RasporedKursa> getRasporedi() {
        return rasporedi;
    }

}
