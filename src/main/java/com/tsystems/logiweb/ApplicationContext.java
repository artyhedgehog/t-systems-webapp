package com.tsystems.logiweb;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import com.tsystems.logiweb.service.ServiceFactory;

/**
 * Application context interface.
 *
 * Provides with basic application components.
 */
public interface ApplicationContext {

    /**
     * Get entity manager factory for a given persistence unit.
     *
     * @param persistenceUnit name of persistence unit in persistence.xml file.
     * @return
     */
    EntityManagerFactory getEntityManagerFactory(String persistenceUnit);

    /**
     * Get entity manager factory for default persistence unit.
     *
     * @return
     */
    EntityManagerFactory getEntityManagerFactory();

    /**
     * Get a factory for services.
     * @return
     */
    ServiceFactory getServiceFactory();

    /**
     * Get logger for the class that needs logging.
     * @param loggedClass
     * @return
     */
    Logger getLogger(Class<?> loggedClass);
}
