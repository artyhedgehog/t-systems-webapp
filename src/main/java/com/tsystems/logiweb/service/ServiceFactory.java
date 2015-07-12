package com.tsystems.logiweb.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import com.tsystems.logiweb.Logiweb;

public class ServiceFactory {

    private final EntityManagerFactory entityManagerFactory;

    public ServiceFactory(final EntityManagerFactory entityManagerFactory) {
        super();
        this.entityManagerFactory = entityManagerFactory;
    }

    public TrucksService getTrucksService() {
        final EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        final Logger logger = Logiweb.getContext()
                                     .getLogger(JPABasedTrucksService.class);
        final TrucksService service = new JPABasedTrucksService(entityManager,
                                                                logger);
        return service;
    }
}
