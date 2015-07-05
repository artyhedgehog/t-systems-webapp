package com.tsystems.logiweb.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Helper static class for performing JPA utility operations.
 */
public final class JpaHelper {
    /**
     * Name of persistence unit to use.
     */
    private static final String PERSISTENCE_UNIT = "logiweb";

    /**
     * Entity manager factory.
     *
     * Factory is only created once for application.
     */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    /**
     * Stub constructor for preventing instantiation.
     */
    private JpaHelper() {
    }

    /**
     * @return the factory for entity managers
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static void main(final String[] args) {
        JpaHelper.getEntityManagerFactory();
    }
}
