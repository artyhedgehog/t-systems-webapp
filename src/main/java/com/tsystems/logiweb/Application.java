package com.tsystems.logiweb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tsystems.logiweb.service.ServiceFactory;

public class Application {
    private static final String ERROR_PERSISTENCE_UNIT_NULL
            = "Persistence unit name cannot be null";

    private static final String PERSISTENCE_UNIT_DEFAULT = "logiweb";

    private static final Application context = new Application();

    private final ReadWriteLock emfLock = new ReentrantReadWriteLock();
    private final Map<String, EntityManagerFactory> entityManagerFactories
            = new HashMap<>();

    private final ReadWriteLock sfLock = new ReentrantReadWriteLock();
    private volatile ServiceFactory serviceFactory;


    public static Application getContext() {
        return context;
    }


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

    public EntityManagerFactory getEntityManagerFactory() {
        return getEntityManagerFactory(PERSISTENCE_UNIT_DEFAULT);
    }


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
}
