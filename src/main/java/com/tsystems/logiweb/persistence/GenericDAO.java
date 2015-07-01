package com.tsystems.logiweb.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Common CRUD data access object interface.
 *
 * Represents an interface to communicate with some persistent storage for some
 * type of entity.
 *
 * @param <E>
 *            Entity class
 * @param <K>
 *            Entity's primary key class
 */
public interface GenericDAO<E, K extends Serializable> {

    /**
     * Create entity in persistent storage.
     *
     * @param entity
     *            Entity to save into the storage
     */
    void create(E entity);

    /**
     * Read all the entities found in the storage.
     *
     * @return list of entities
     */
    List<E> read();

    /**
     * Read one entity by it's primary key.
     *
     * @param id
     *            Primary key to find entity by
     * @return entity found in storage by given id
     */
    E read(K id);

    /**
     * Find entities similar to given sample.
     *
     * Result list include all and only the entities with same fields values as
     * non-null fields of the sample
     *
     * @param entitySample
     *            Sample for searching for.
     * @return Found entities list.
     */
    List<E> findBySample(E entitySample);

    /**
     * Update the stored entity with the entity given.
     *
     * @param entiry
     *            Entity with properties set to what should be saved
     * @return the same entity after merging with storage version
     */
    E update(E entiry);

    /**
     * Delete given entity from the storage.
     *
     * @param entity
     *            Entity to delete
     */
    void delete(E entity);

    /**
     * Delete entity from storage by given id.
     *
     * @param id
     *             Entity primary key.
     */
    void delete(K id);
}
