package com.tsystems.logiweb.service;

import java.util.List;

import com.tsystems.logiweb.entities.Truck;
import com.tsystems.logiweb.entities.TruckCondition;

/**
 *
 */
public interface TrucksService {
    /**
     * @return
     */
    List<Truck> getTrucksList();

    /**
     * @param id
     * @return
     * @throws ServiceException
     */
    Truck getTruck(Integer id) throws ServiceException;

    /**
     * @param registrationNumber
     * @param driversQuantity
     * @param capacityInTons
     * @param conditionId
     * @param townId
     * @return
     * @throws ServiceException
     */
    Truck addTruck(String registrationNumber, Byte driversQuantity,
                  Float capacityInTons, Integer conditionId, Integer townId)
            throws ServiceException;

    /**
     * @param truckId
     * @param registrationNumber
     * @param driversQuantity
     * @param capacityInTons
     * @param conditionId
     * @param townId
     * @return
     * @throws ServiceException
     */
    Truck modifyTruck(Integer truckId, String registrationNumber,
                      Byte driversQuantity, Float capacityInTons,
                      Integer conditionId, Integer townId)
            throws ServiceException;

    /**
     * @param truckId
     */
    void removeTruck(Integer truckId);

    /**
     * Get list of all truck conditions.
     * @return
     */
    List<TruckCondition> getConditionsList();
}
