package com.tsystems.logiweb.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ServiceFactory {

    private final EntityManagerFactory entityManagerFactory;

    public ServiceFactory(final EntityManagerFactory entityManagerFactory) {
        super();
        this.entityManagerFactory = entityManagerFactory;
    }

    public TrucksService getTrucksService() {
        final EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        final TrucksService service = new JPABasedTrucksService(entityManager);
        return service;
    }
}
