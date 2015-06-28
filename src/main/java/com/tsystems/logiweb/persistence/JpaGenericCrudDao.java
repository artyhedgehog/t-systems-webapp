package com.tsystems.logiweb.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Implementation of generic DAO for JPA.
 * 
 * @param <E>
 *            entity class
 * @param <K>
 *            primary key of entity
 */
public class JpaGenericCrudDao<E, K extends Serializable> implements
        GenericCrudDao<E, K> {

    /**
     * Entity manager used by DAO instance.
     */
    private EntityManager entityManager;

    /**
     * 
     */
    private Class<E> entityType;

    /**
     * @param manager
     *            Entity manager for DAO instance to use.
     * @param type
     *            Class of managed entity.
     */
    public JpaGenericCrudDao(final EntityManager manager, final Class<E> type) {
        entityManager = manager;
        entityType = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.persistence.GenericCrudDao#create(java.lang.Object)
     */
    @Override
    public final void create(final E entity) {
        entityManager.persist(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.persistence.GenericCrudDao#read()
     */
    @Override
    public final List<E> read() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> criteria = builder.createQuery(entityType);
        final TypedQuery<E> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.persistence.GenericCrudDao#read(java.io.Serializable)
     */
    @Override
    public final E read(final K id) {
        return entityManager.find(entityType, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.persistence.GenericCrudDao#update(java.lang.Object)
     */
    @Override
    public final E update(final E entiry) {
        return entityManager.merge(entiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.persistence.GenericCrudDao#delete(java.lang.Object)
     */
    @Override
    public final void delete(final E entity) {
        entityManager.remove(entityManager.merge(entity));
    }
}
