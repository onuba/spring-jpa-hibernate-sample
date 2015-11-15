package org.onuba.example.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.onuba.example.dao.DAOView;

/**
 * Dao para vistas que no soporta actualizaciones.
 * 
 * @author fhernandez
 *
 * @param <E>
 * @param <K>
 */
public class DAOViewImpl implements DAOView {

    @PersistenceContext
    protected EntityManager entityManager;

    public Query createNativeQuery(String query) {
        return entityManager.createNativeQuery(query);
    }

}
