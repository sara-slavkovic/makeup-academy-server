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
public class UcitajKorisnika extends AbstractGenericOperation {

    private GenericEntity genericEntity;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Korisnik)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Korisnik k = (Korisnik) param;
        genericEntity = repository.nadji(k, k.getIdKorisnika());
    }

    public GenericEntity getGenericEntity() {
        return genericEntity;
    }

}
