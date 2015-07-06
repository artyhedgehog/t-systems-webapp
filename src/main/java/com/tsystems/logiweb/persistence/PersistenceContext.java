package com.tsystems.logiweb.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceContext {
    private static PersistenceContext instance = new PersistenceContext();

    private EntityManagerFactory entityManagerFactory;

    public static PersistenceContext getContext() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (null == entityManagerFactory) {
            entityManagerFactory = Persistence
                    .createEntityManagerFactory(getPersistenceUnitName());
        }
        return entityManagerFactory;
    }

    public String getPersistenceUnitName() {
        // TODO Auto-generated method stub
        return null;
    }
}
