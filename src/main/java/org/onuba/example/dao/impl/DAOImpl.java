package org.onuba.example.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.onuba.example.dao.DAO;

/**
 * Implementación basica de un DAO
 * 
 * @author fhernandez
 * 
 * @param <E>
 *            Tipo de entity con el que trabaja
 * @param <K>
 *            Tipo de la clave primaria del entity
 */
public class DAOImpl<E, K> implements DAO<E, K> {

    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public DAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void persist(E entity) {
        entityManager.persist(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

    public Query createNamedQuery(String query) {
        return entityManager.createNamedQuery(query);
    }
    
    public Query createNativeQuery(String query, Class<E> clazz) {
        return entityManager.createNativeQuery(query, clazz);
    }

    @SuppressWarnings("unchecked")
    public E unique(Query q) {
        return (E) q.getSingleResult();
    }

    public E update(E entity) {
        return entityManager.merge(entity);
    }

    @SuppressWarnings("unchecked")
    public List<E> list(Query q) {
        return q.getResultList();
    }

    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }
}
