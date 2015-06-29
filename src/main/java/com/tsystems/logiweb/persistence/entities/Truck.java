package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the trucks database table.
 * 
 */
@Entity
@Table(name = "trucks")
public class Truck implements Serializable {
    /**
     * Utility serialization property.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 
     */
    @Column(name = "capacity_tons")
    private Float capacityTons;

    /**
     * 
     */
    @Column(name = "drivers_quantity")
    private Byte driversQuantity;

    /**
     * 
     */
    @Column(name = "reg_number")
    private String regNumber;

    // bi-directional many-to-one association to DriverState
    /**
     * 
     */
    @OneToMany(mappedBy = "truck")
    private Set<DriverState> driversStates;

    // bi-directional many-to-one association to Order
    /**
     * 
     */
    @OneToMany(mappedBy = "truck")
    private Set<Order> orders;

    // bi-directional one-to-one association to TruckState
    /**
     * 
     */
    @OneToOne(mappedBy = "truck")
    private TruckState trucksState;

    /**
     * @return
     */
    public final int getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public final Truck setId(final int id) {
        this.id = id;
        return this;
    }

    /**
     * @return
     */
    public final Float getCapacityTons() {
        return this.capacityTons;
    }

    /**
     * @param capacityTons
     */
    public final Truck setCapacityTons(final Float capacityTons) {
        this.capacityTons = capacityTons;
        return this;
    }

    /**
     * @return
     */
    public final Byte getDriversQuantity() {
        return this.driversQuantity;
    }

    /**
     * @param driversQuantity
     */
    public final Truck setDriversQuantity(final Byte driversQuantity) {
        this.driversQuantity = driversQuantity;
        return this;
    }

    /**
     * @return
     */
    public final String getRegNumber() {
        return this.regNumber;
    }

    /**
     * @param regNumber
     */
    public final Truck setRegNumber(final String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    /**
     * @return
     */
    public final Set<DriverState> getDriversStates() {
        return this.driversStates;
    }

    /**
     * @param driversStates
     */
    public final Truck setDriversStates(final Set<DriverState> driversStates) {
        this.driversStates = driversStates;
        return this;
    }

    /**
     * @param driversState
     * @return
     */
    public final DriverState addDriversState(final DriverState driversState) {
        getDriversStates().add(driversState);
        driversState.setTruck(this);

        return driversState;
    }

    /**
     * @param driversState
     * @return
     */
    public final DriverState removeDriversState(final DriverState driversState) {
        getDriversStates().remove(driversState);
        driversState.setTruck(null);

        return driversState;
    }

    /**
     * @return
     */
    public final Set<Order> getOrders() {
        return this.orders;
    }

    /**
     * @param orders
     */
    public final Truck setOrders(final Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    /**
     * @param order
     * @return
     */
    public final Order addOrder(final Order order) {
        getOrders().add(order);
        order.setTruck(this);

        return order;
    }

    /**
     * @param order
     * @return
     */
    public final Order removeOrder(final Order order) {
        getOrders().remove(order);
        order.setTruck(null);

        return order;
    }

    /**
     * @return
     */
    public final TruckState getTrucksState() {
        return this.trucksState;
    }

    /**
     * @param trucksState
     */
    public final Truck setTrucksState(final TruckState trucksState) {
        this.trucksState = trucksState;
        return this;
    }
}
