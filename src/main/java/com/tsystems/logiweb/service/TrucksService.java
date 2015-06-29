package com.tsystems.logiweb.service;

import java.util.List;

import com.tsystems.logiweb.persistence.entities.Truck;

/**
 * @author artyhedgehog
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
     */
    Truck getTruck(Integer id);

    /**
     * @param registrationNumber
     * @param driversQuantity
     * @param capacityInTons
     * @param condition
     * @param town
     * @return
     */
    void addTruck(String registrationNumber, Byte driversQuantity,
            Float capacityInTons, String condition, String town);

    /**
     * @param registrationNumber
     * @param driversQuantity
     * @param capacityInTons
     * @param condition
     * @param town
     * @return
     */
    Truck modifyTruck(String registrationNumber, Integer driversQuantity,
            Float capacityInTons, String condition, String town);

    /**
     * @param truckId
     */
    void removeTruck(Integer truckId);
}
