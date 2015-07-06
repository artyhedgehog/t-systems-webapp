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
public class JPAGenericDAO<E, K extends Serializable>
        implements GenericDAO<E, K> {

    /**
     * Entity manager used by DAO instance.
     */
    private final EntityManager entityManager;

    /**
     *
     */
    private final Class<E> entityType;

    /**
     * @param manager
     *            Entity manager for DAO instance to use.
     * @param entityClass
     *            Class of managed entity.
     */
    public JPAGenericDAO(final EntityManager manager,
                         final Class<E> entityClass) {
        entityManager = manager;
        entityType = entityClass;
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#create(java.lang.Object)
     */
    @Override
    public final void create(final E entity) {
        entityManager.persist(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#read()
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
     * @see GenericDAO#read(java.io.Serializable
     */
    @Override
    public final E read(final K id) {
        return entityManager.find(entityType, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#update(java.lang.Object)
     */
    @Override
    public final E update(final E entiry) {
        return entityManager.merge(entiry);
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public final void delete(final E entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public final void delete(final K id) {
        entityManager.remove(entityManager.find(entityType, id));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.tsystems.logiweb.persistence.GenericDAO#findBySample(java.lang
     * .Object)
     */
    @Override
    public List<E> findBySample(final E entitySample) {
        final List<E> all = read();
        final List<E> found = new ArrayList<>();
        for (final E entity : all) {
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
    private boolean fitSample(final E entitySample, final E entity) {
        for (final Field field : entityType.getDeclaredFields()) {
            if (null != field.getAnnotation(Column.class)) {
                try {
                    final Object value = field.get(entitySample);
                    if (null != value && value != field.get(entity)) {
                        return false;
                    }
                } catch (final IllegalArgumentException e) {
                    // TODO log exception
                    return false;
                } catch (final IllegalAccessException e) {
                    // TODO log exception
                    return false;
                }
            }
        }
        return true;
    }
}
