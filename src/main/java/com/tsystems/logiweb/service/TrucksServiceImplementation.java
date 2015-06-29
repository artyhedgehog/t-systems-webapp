/**
 * 
 */
package com.tsystems.logiweb.service;

import java.util.List;

import com.tsystems.logiweb.persistence.GenericCrudDao;
import com.tsystems.logiweb.persistence.JpaGenericCrudDao;
import com.tsystems.logiweb.persistence.JpaHelper;
import com.tsystems.logiweb.persistence.entities.Truck;

/**
 * 
 */
public class TrucksServiceImplementation implements TrucksService {

    private final GenericCrudDao<Truck, Integer> dao;

    public TrucksServiceImplementation() {
        dao = new JpaGenericCrudDao<Truck, Integer>(JpaHelper
                .getEntityManagerFactory().createEntityManager(), Truck.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.service.TrucksService#getTrucksList()
     */
    @Override
    public List<Truck> getTrucksList() {
        return dao.read();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tsystems.logiweb.service.TrucksService#getTruck(java.lang.Long)
     */
    @Override
    public Truck getTruck(Integer id) {
        return dao.read(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tsystems.logiweb.service.TrucksService#addTruck(java.lang.String,
     * java.lang.Integer, java.lang.Float, java.lang.String, java.lang.String)
     */
    @Override
    public void addTruck(String registrationNumber, Byte driversQuantity,
            Float capacityInTons, String condition, String town) {
        Truck truck = new Truck().setRegNumber(registrationNumber)
                .setDriversQuantity(driversQuantity)
                .setCapacityTons(capacityInTons);
        // TODO: implement joining condition and town
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tsystems.logiweb.service.TrucksService#modifyTruck(java.lang.String,
     * java.lang.Integer, java.lang.Float, java.lang.String, java.lang.String)
     */
    @Override
    public Truck modifyTruck(String registrationNumber,
            Integer driversQuantity, Float capacityInTons, String condition,
            String town) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tsystems.logiweb.service.TrucksService#removeTruck(java.lang.Integer)
     */
    @Override
    public void removeTruck(Integer truckId) {
        // TODO Auto-generated method stub

    }
}
