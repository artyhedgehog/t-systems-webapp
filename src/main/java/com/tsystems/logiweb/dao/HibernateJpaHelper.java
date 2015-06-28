package com.tsystems.logiweb.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJpaHelper {
    private static final String PERSISTENCE_UNIT = "logiweb";
    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory(PERSISTENCE_UNIT);

    /**
     * @return the factory for entity managers
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
