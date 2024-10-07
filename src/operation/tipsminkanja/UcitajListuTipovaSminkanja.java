/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.tipsminkanja;

import domain.TipSminkanja;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Korisnik
 */
public class UcitajListuTipovaSminkanja extends AbstractGenericOperation {

    List<TipSminkanja> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof TipSminkanja)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.vratiSve((TipSminkanja) param);
    }

    public List<TipSminkanja> getList() {
        return list;
    }
    
    
}
