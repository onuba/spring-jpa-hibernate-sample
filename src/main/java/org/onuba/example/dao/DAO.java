package org.onuba.example.dao;

import java.util.List;

import javax.persistence.Query;

/**
 * 
 * @author fhernandez
 * 
 * @param <E>
 *            Object of type Entity
 * @param <K>
 *            Object for the primary key type of the entity
 */
public interface DAO<E, K> {

    void persist(E entity);

    void remove(E entity);

    Query createNamedQuery(String query);
    
    Query createNativeQuery(String query, Class<E> clazz);

    E unique(Query q);

    E update(E entity);

    List<E> list(Query q);

    E findById(K id);
}
