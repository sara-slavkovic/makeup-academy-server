/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.kurs;

import domain.GenericEntity;
import domain.Kurs;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajListuKurseva extends AbstractGenericOperation {

    List<GenericEntity> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Kurs)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.vratiSve((Kurs) param);//poziva se dbb
    }

    public List<GenericEntity> getList() {
        return list;
    }
}
