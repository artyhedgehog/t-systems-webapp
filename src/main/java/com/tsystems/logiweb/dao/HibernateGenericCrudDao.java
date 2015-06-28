package com.tsystems.logiweb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * 
 * @param <E>
 * @param <K>
 */
public abstract class HibernateGenericCrudDao<E, K extends Serializable>
        implements GenericCrudDao<E, K> {

    /**
     * 
     */
    protected EntityManager entityManager;

    /**
     * 
     */
    private Class<E> entityClass;

    /**
     * @param manager
     */
    public HibernateGenericCrudDao(EntityManager manager, Class<E> entityClass) {
        entityManager = manager;
        this.entityClass = entityClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.dao.GenericCrudDao#create(java.lang.Object)
     */
    @Override
    public void create(E entity) {
        entityManager.persist(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.dao.GenericCrudDao#read()
     */
    @Override
    public List<E> read() {
        return entityManager.createNamedQuery(getFindAllQueryName(),
                entityClass).getResultList();
    }

    /**
     * Name for named query used for find all entities.
     * 
     * @return
     */
    protected abstract String getFindAllQueryName();

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.dao.GenericCrudDao#read(java.io.Serializable)
     */
    @Override
    public E read(K id) {
        return entityManager.find(entityClass, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.dao.GenericCrudDao#update(java.lang.Object)
     */
    @Override
    public E update(E entiry) {
        return entityManager.merge(entiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.dao.GenericCrudDao#delete(java.lang.Object)
     */
    @Override
    public void delete(E entity) {
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
    }

}
