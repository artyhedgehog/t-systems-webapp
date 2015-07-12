package com.tsystems.logiweb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.tsystems.logiweb.service.ServiceFactory;

/**
 * Logiweb application implementation of application context.
 *
 * Implementation is a thread-safe singleton with access to an instance via
 * getContext() method.
 */
public class Logiweb implements ApplicationContext {
    private static final String ERROR_PERSISTENCE_UNIT_NULL
            = "Persistence unit name cannot be null";

    private static final String PERSISTENCE_UNIT_DEFAULT = "logiweb";

    private static final ApplicationContext context = new Logiweb();

    private final ReadWriteLock emfLock = new ReentrantReadWriteLock();
    private final Map<String, EntityManagerFactory> entityManagerFactories
            = new HashMap<>();

    private final ReadWriteLock sfLock = new ReentrantReadWriteLock();
    private volatile ServiceFactory serviceFactory;


    public static ApplicationContext getContext() {
        return context;
    }


    /* (non-Javadoc)
     * @see com.tsystems.logiweb.ApplicationContext#getEntityManagerFactory(java.lang.String)
     */
    @Override
    public EntityManagerFactory getEntityManagerFactory(
            final String persistenceUnit) {
        if (null == persistenceUnit) {
            throw new IllegalArgumentException(ERROR_PERSISTENCE_UNIT_NULL);
        }

        emfLock.readLock().lock();
        EntityManagerFactory emf = entityManagerFactories.get(persistenceUnit);
        if (null == emf) {
            emfLock.readLock().unlock();
            emfLock.writeLock().lock();

            /*
             *  Make sure no one set the entity manager factory until write
             *  lock has been acquired.
             */
            emf = entityManagerFactories.get(persistenceUnit);
            if (null == emf) {
                emf = Persistence.createEntityManagerFactory(persistenceUnit);
                entityManagerFactories.put(persistenceUnit, emf);
            }

            emfLock.writeLock().unlock();
        } else {
            emfLock.readLock().unlock();
        }

        return emf;
    }

    /* (non-Javadoc)
     * @see com.tsystems.logiweb.ApplicationContext#getEntityManagerFactory()
     */
    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return getEntityManagerFactory(PERSISTENCE_UNIT_DEFAULT);
    }


    /* (non-Javadoc)
     * @see com.tsystems.logiweb.ApplicationContext#getServiceFactory()
     */
    @Override
    public ServiceFactory getServiceFactory() {
        sfLock.readLock().lock();
        if (null == serviceFactory) {
            sfLock.readLock().unlock();
            sfLock.writeLock().lock();

            /*
             *  Make sure no one set the service factory until write lock
             *  has been acquired.
             */
            if (null == serviceFactory) {
                serviceFactory = new ServiceFactory(getEntityManagerFactory());
            }

            sfLock.writeLock().unlock();
        } else {
            sfLock.readLock().unlock();
        }

        return serviceFactory;
    }


    @Override
    public Logger getLogger(final Class<?> loggedClass) {
        return Logger.getLogger(loggedClass);
    }
}
