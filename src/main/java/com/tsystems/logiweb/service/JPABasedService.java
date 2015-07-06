package com.tsystems.logiweb.service;

import javax.persistence.EntityManager;

import com.tsystems.logiweb.persistence.JpaHelper;
import com.tsystems.logiweb.persistence.PersistenceContext;

/**
 * Basic framework for a service, working with persistence through JPA.
 *
 * Each service method of subclasses ought to follow one of the two workflows:
 * <ul>
 *   <li>Simple - using service compound methods:
 *     <ol>
 *       <li>Call `startService()` - to create and get entity manager and also
 *              begin transaction.
 *       <li>Perform specific domain logic with persistence entities using the
 *              entity manager provided.
 *       <li>If all goes well, call `finishService()` - to commit the changes
 *              made and close entity manager.
 *       <li>If operation fails, call `stopService()` - which rolls back
 *              the changes and close entity manager.
 *     </ol>
 *   <li>Advanced - using lower-level proxy-methods:
 *     <ol>
 *       <li>Call `prepareEntityManager()` to create entity manager.
 *       <li>Call `beginTransaction()` if necessary.
 *       <li>Call `getEntityManager()` to get a copy to work with.
 *       <li>Perform specific domain logic with persistence entities using the
 *              entity manager provided.
 *       <li>If transaction have been opened, either call commitTransaction()
 *              or rollbackTransaction().
 *       <li>Repeat steps 2-5 if necessary.
 *       <li>Call closeEntityManager() at the end.
 *     </ol>
 * </ul>
 */
abstract public class JPABasedService {

    /**
     * Entity manager instance.
     */
    private EntityManager entityManager = null;

    /**
     * Start service and open a transaction.
     *
     * @return Entity manager to work with.
     */
    protected EntityManager startService() {
        initEntityManager();
        beginTransaction();
        return getEntityManager();
    }

    /**
     * Apply the changes and close the manager.
     */
    protected void finishService() {
        commitTransaction();
        closeEntityManager();
    }

    /**
     * Cancel the changes and close the manager.
     */
    protected void stopService() {
        rollbackTransaction();
        closeEntityManager();
    }

    /**
     * Clean up service.
     *
     * Roll back transaction if it is active and close the entity manager.
     */
    protected void cleanupService() {
        rollbackTransactionIfActive();
        closeEntityManager();
    }


    /**
     * Initialize entity manager.
     */
    protected void initEntityManager() {
        entityManager = PersistenceContext.getContext()
                                          .getEntityManagerFactory()
                                          .createEntityManager();
    }

    /**
     * Begin transaction bound with the entity manager used.
     */
    protected void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    /**
     * Get entity manager. Initialize if haven't yet.
     * @return Entity manager instance for current service.
     */
    protected EntityManager getEntityManager() {
        if (null == entityManager) {
            initEntityManager();
        }
        return entityManager;
    }

    /**
     * Commit the transaction bound to the entity manager used.
     */
    protected void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    /**
     * Roll back the transaction bound to the entity manager used.
     */
    protected void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

    /**
     * Roll back the transaction if it is active.
     */
    protected void rollbackTransactionIfActive() {
        if (getEntityManager().getTransaction().isActive()) {
            rollbackTransaction();
        }
    }

    /**
     * Close entity manager.
     */
    protected void closeEntityManager() {
        getEntityManager().close();
    }
}
