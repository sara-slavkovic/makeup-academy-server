/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Korisnik
 */
public abstract class AbstractGenericOperation {
    //ovo je apstraktna klasa koja ima svoje apstraktne metode i sve ostale klase obicne koje nasledjuju apstraktnu klasu
    //obicne klase moraju da override-uju apstraktne metode i apstraktne klase
    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();//on nasledjuje repository interfejs
    }
    
    public final void execute(Object param) throws Exception{
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();            
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }finally{
            disconnect();
        }
    }

    // Operation-specific method
    //protected sva deca vide ovu metodu
    protected abstract void preconditions(Object param) throws Exception;//uslovi koje mora da zadovolji ovaj parametar koji se salje

    private void startTransaction() throws Exception{
        ((DbRepository) repository).connect();//ovo je kao konekcija.getinst.getconnection
    }

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;//izvrsavanje te operacije

    private void commitTransaction() throws Exception{
        ((DbRepository) repository).commit();//ovde se dodatno kreira fja commit
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();//kreira se metoda rollback
    }

    private void disconnect() throws Exception{
        ((DbRepository) repository).disconnect();//kreira se metoda disconnect
    }
    
    
}
