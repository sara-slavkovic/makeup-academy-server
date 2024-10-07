/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.grupa;

import domain.Grupa;
import domain.RasporedKursa;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class KreirajGrupu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Grupa)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        //da se odmah dodaju i grupa i rk u zasebne tabele
        Grupa g = (Grupa) param;
        repository.dodaj(g);
        List<RasporedKursa> rasporedi = g.getRasporediKurseva();
        for (RasporedKursa rasporedKursa : rasporedi) {
            repository.dodaj(rasporedKursa);
        }
    }

}
