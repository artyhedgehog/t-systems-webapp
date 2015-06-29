package com.tsystems.logiweb.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
     * @see GenericCrudDao#create(java.lang.Object)
     */
    @Override
    public final void create(final E entity) {
        entityManager.persist(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see GenericCrudDao#read()
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
     * @see GenericCrudDao#read(java.io.Serializable
     */
    @Override
    public final E read(final K id) {
        return entityManager.find(entityType, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see GenericCrudDao#update(java.lang.Object)
     */
    @Override
    public final E update(final E entiry) {
        return entityManager.merge(entiry);
    }

    /*
     * (non-Javadoc)
     * 
     * @see GenericCrudDao#delete(java.lang.Object)
     */
    @Override
    public final void delete(final E entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tsystems.logiweb.persistence.GenericCrudDao#findBySample(java.lang
     * .Object)
     */
    @Override
    public List<E> findBySample(final E entitySample) {
        final List<E> all = read();
        final List<E> found = new ArrayList<>();
        for (E entity : all) {
            if (fitSample(entitySample, entity)) {
                found.add(entity);
            }
        }
        return found;
    }

    /**
     * @param entitySample
     * @param entity
     * @return
     */
    private boolean fitSample(E entitySample, E entity) {
        for (Field field : entityType.getDeclaredFields()) {
            if (null != field.getAnnotation(Column.class)) {
                try {
                    Object value = field.get(entitySample);
                    if (null != value && value != field.get(entity)) {
                        return false;
                    }
                } catch (IllegalArgumentException e) {
                    // TODO log exception
                    return false;
                } catch (IllegalAccessException e) {
                    // TODO log exception
                    return false;
                }
            }
        }
        return true;
    }
}
