/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.prijava;

import domain.GenericEntity;
import domain.Prijava;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajListuPrijava extends AbstractGenericOperation {

    private List<GenericEntity> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Prijava)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.vratiSve((Prijava) param);
    }

    public List<GenericEntity> getList() {
        return list;
    }

}
