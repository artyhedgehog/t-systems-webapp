/**
 *
 */
package com.tsystems.logiweb.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.tsystems.logiweb.entities.Town;
import com.tsystems.logiweb.entities.Truck;
import com.tsystems.logiweb.entities.TruckCondition;
import com.tsystems.logiweb.entities.TruckState;
import com.tsystems.logiweb.persistence.JPAGenericDAO;

/**
 * Implementation of the trucks service based on JPA persistence storage.
 */
public class JPABasedTrucksService extends JPABasedService
                                   implements TrucksService {

    public JPABasedTrucksService(final EntityManager entityManager) {
        super(entityManager);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.tsystems.logiweb.service.TrucksService#getTrucksList()
     */
    @Override
    public List<Truck> getTrucksList() {
        try {
            return buildTruckDAO(getEntityManager()).read();
        } finally {
            closeEntityManager();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.tsystems.logiweb.service.TrucksService#getTruck(java.lang.Long)
     */
    @Override
    public Truck getTruck(final Integer id) {
        try {
            return buildTruckDAO(getEntityManager()).read(id);
        } finally {
            closeEntityManager();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.tsystems.logiweb.service.TrucksService#addTruck(java.lang.String,
     * java.lang.Integer, java.lang.Float, java.lang.String, java.lang.String)
     */
    @Override
    public void addTruck(final String registrationNumber,
                         final Byte driversQuantity,
                         final Float capacityInTons,
                         final Integer conditionId,
                         final Integer townId) throws ServiceException {
        try {
            final EntityManager manager = startService();

            final TruckCondition condition = buildTruckConditionDAO(manager)
                    .read(conditionId);
            final Town town = (null != townId)
                    ? buildTownDAO(manager).read(townId)
                    : null;

            final Truck truck = new Truck().setRegNumber(registrationNumber)
                                           .setDriversQuantity(driversQuantity)
                                           .setCapacityTons(capacityInTons);
            buildTruckDAO(manager).create(truck);

            final TruckState state = new TruckState().setTruck(truck)
                    .setCondition(condition).setTown(town);
            buildTruckStateDAO(manager).create(state);

            commitTransaction();
        } catch (final PersistenceException exception) {
            throw new ServiceException("Could not add truck.", exception);
        } finally {
            cleanupService();
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.tsystems.logiweb.service.TrucksService#modifyTruck(java.lang.String,
     * java.lang.Integer, java.lang.Float, java.lang.String, java.lang.String)
     */
    @Override
    public Truck modifyTruck(final Integer truckId,
                             final String registrationNumber,
                             final Byte driversQuantity,
                             final Float capacityInTons,
                             final Integer conditionId,
                             final Integer townId) {
        try {
            final EntityManager manager = startService();

            final Truck truck = buildTruckDAO(manager).read(truckId);
            truck.setRegNumber(registrationNumber)
                 .setDriversQuantity(driversQuantity)
                 .setCapacityTons(capacityInTons);

            final TruckCondition condition = buildTruckConditionDAO(manager)
                    .read(conditionId);
            final TruckState state = truck.getState();
            final Town town = buildTownDAO(manager).read(townId);
            state.setCondition(condition).setTown(town);

            commitTransaction();
            return truck;
        } finally {
            cleanupService();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.tsystems.logiweb.service.TrucksService#removeTruck(java.lang.Integer)
     */
    @Override
    public void removeTruck(final Integer truckId) {
        try {
            buildTruckDAO(getEntityManager()).delete(truckId);
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public List<TruckCondition> getConditionsList() {
        try {
            return buildTruckConditionDAO(getEntityManager()).read();
        } finally {
            closeEntityManager();
        }
    }


    private JPAGenericDAO<Truck, Integer> buildTruckDAO(
            final EntityManager entityManager) {
        return new JPAGenericDAO<>(entityManager, Truck.class);
    }

    private JPAGenericDAO<TruckState, Integer> buildTruckStateDAO(
            final EntityManager entityManager) {
        return new JPAGenericDAO<>(entityManager, TruckState.class);
    }

    private JPAGenericDAO<Town, Integer> buildTownDAO(
            final EntityManager entityManager) {
        return new JPAGenericDAO<>(entityManager, Town.class);
    }

    private JPAGenericDAO<TruckCondition, Integer> buildTruckConditionDAO(
            final EntityManager entityManager) {
        return new JPAGenericDAO<>(entityManager, TruckCondition.class);
    }
}
