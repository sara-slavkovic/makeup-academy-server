/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.GenericEntity;
import repository.db.DbRepository;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Korisnik
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {
    //mora da overrideuje sve metode iz interfejsa repository jer dbrepository nasledjuje repository
    //dbbroker 

    @Override
    public void dodaj(GenericEntity entity) throws Exception {
        try {
            String query = "INSERT INTO " + entity.getTableName() + "(" + entity.getAttributeNames()
                    + ") VALUES(" + entity.getUnknownValues() + ")";
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            entity.prepareStatement(preparedStatement, entity);//postavlja ps.setstring(1,"ime")...
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> vratiSve(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getOrderCondition();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void promeni(GenericEntity entity) throws Exception {
        try {
            String query = "UPDATE " + entity.getTableName() + " SET " + entity.getUpdateQuery()
                    + " WHERE id=" + entity.getID(entity);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            entity.prepareStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void obrisi(GenericEntity entity) throws Exception {
        try {
            String query = "DELETE FROM " + entity.getTableName() + " WHERE id=" + entity.getID(entity);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public GenericEntity uloguj(GenericEntity entity, String username, String password) throws Exception {
        GenericEntity genericEntity = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE korisnickoIme='" + username
                    + "' AND lozinka='" + password + "'";
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            genericEntity = entity.getResult(resultSet);
            resultSet.close();
            statement.close();
            return genericEntity;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> nadjiPoKorisnickomImenuIMejlu(GenericEntity entity, String mejl, String korisnickoIme) throws Exception {
        List<GenericEntity> lista = null;
        try {
            String query = "SELECT * FROM korisnik WHERE mejl='" + mejl + "' AND korisnickoIme='" + korisnickoIme + "'";
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = entity.getList(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return lista;
    }

    @Override
    public GenericEntity nadji(GenericEntity entity, int id) throws Exception {
        GenericEntity genericEntity = null;
        try {
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE id=" + id;
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            genericEntity = entity.getResult(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return genericEntity;
    }

    @Override
    public List<GenericEntity> filter(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            //da vrati listu objekata po imenu npr iz pretrage entity.getnaziv..
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getCondition(entity)
                    + " ORDER BY " + entity.getOrderCondition();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int vratiMaksId(GenericEntity entity) throws Exception {
        int id = 0;
        try {
            String query = "SELECT MAX(id) AS maks FROM " + entity.getTableName();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("maks");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return id + 1;
    }

    @Override
    public void promeniPrijavu(GenericEntity entity) throws Exception {
        try {
            String query = "UPDATE " + entity.getTableName() + " SET " + entity.getUpdateQuery() + " WHERE " + entity.getID(entity);
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            entity.prepareStatement(ps, entity);
            System.out.println("Generated query: " + query);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }




}
