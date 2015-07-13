package com.tsystems.logiweb.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.tsystems.logiweb.Logiweb;

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

    private static final String ERROR_COMPARE_WITH_SAMPLE = "Failed comparing entity with sample.";

    protected Logger log;

    private static final String ERROR_ARGUMENT_NULL = "Argument must not be null.";

    private static final String
            ERROR_NOT_FOUND = "No entity of class %s with id %d found.";

    /**
     * Entity manager used by DAO instance.
     */
    private final EntityManager entityManager;

    /**
     *
     */
    private final Class<E> entityClass;

    /**
     * @param manager
     *            Entity manager for DAO instance to use.
     * @param entityClass
     *            Class of managed entity.
     */
    public JPAGenericDAO(final EntityManager manager,
                         final Class<E> entityClass) {
        entityManager = manager;
        this.entityClass = entityClass;
        log = Logiweb.getContext().getLogger(getClass());
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#create(java.lang.Object)
     */
    @Override
    public final void create(final E entity) {
        if (null == entity) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
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
        final CriteriaQuery<E> criteria = builder.createQuery(entityClass);
        final Root<E> root = criteria.from(entityClass);
        criteria.select(root);
        final TypedQuery<E> typed = entityManager.createQuery(criteria);
        return typed.getResultList();
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#read(java.io.Serializable)
     */
    @Override
    public final E read(final K id) {
        if (null == id) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
        return entityManager.find(entityClass, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#update(java.lang.Object)
     */
    @Override
    public final E update(final E entity) {
        if (null == entity) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
        return entityManager.merge(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see GenericDAO#delete(java.lang.Object)
     */
    @Override
    public final void delete(final E entity) {
        if (null == entity) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public final void delete(final K id) {
        if (null == id) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
        final E entity = read(id);
        if (null == entity) {
            throw new PersistenceException(
                    String.format(ERROR_NOT_FOUND, entityClass, id));
        }
        entityManager.remove(entity);
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
        if (null == entitySample) {
            throw new IllegalArgumentException(ERROR_ARGUMENT_NULL);
        }
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
        for (final Field field : entityClass.getDeclaredFields()) {
            if (null != field.getAnnotation(Column.class)) {
                try {
                    final Object value = field.get(entitySample);
                    if (null != value && value != field.get(entity)) {
                        return false;
                    }
                } catch (final IllegalArgumentException
                         | IllegalAccessException e) {
                    log.error(ERROR_COMPARE_WITH_SAMPLE, e);
                    return false;
                }
            }
        }
        return true;
    }
}
