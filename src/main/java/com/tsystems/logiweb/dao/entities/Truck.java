package com.tsystems.logiweb.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the trucks database table.
 * 
 */
@Entity
@Table(name = "trucks")
@NamedQuery(name = "Truck.findAll", query = "SELECT t FROM Truck t")
public class Truck implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "capacity_tons")
    private float capacityTons;

    @Column(name = "drivers_quantity")
    private byte driversQuantity;

    @Column(name = "reg_number")
    private String regNumber;

    // bi-directional many-to-one association to DriverState
    @OneToMany(mappedBy = "truck")
    private Set<DriverState> driversStates;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "truck")
    private Set<Order> orders;

    // bi-directional one-to-one association to TruckState
    @OneToOne(mappedBy = "truck")
    private TruckState trucksState;

    public Truck() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCapacityTons() {
        return this.capacityTons;
    }

    public void setCapacityTons(float capacityTons) {
        this.capacityTons = capacityTons;
    }

    public byte getDriversQuantity() {
        return this.driversQuantity;
    }

    public void setDriversQuantity(byte driversQuantity) {
        this.driversQuantity = driversQuantity;
    }

    public String getRegNumber() {
        return this.regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Set<DriverState> getDriversStates() {
        return this.driversStates;
    }

    public void setDriversStates(Set<DriverState> driversStates) {
        this.driversStates = driversStates;
    }

    public DriverState addDriversState(DriverState driversState) {
        getDriversStates().add(driversState);
        driversState.setTruck(this);

        return driversState;
    }

    public DriverState removeDriversState(DriverState driversState) {
        getDriversStates().remove(driversState);
        driversState.setTruck(null);

        return driversState;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Order addOrder(Order order) {
        getOrders().add(order);
        order.setTruck(this);

        return order;
    }

    public Order removeOrder(Order order) {
        getOrders().remove(order);
        order.setTruck(null);

        return order;
    }

    public TruckState getTrucksState() {
        return this.trucksState;
    }

    public void setTrucksState(TruckState trucksState) {
        this.trucksState = trucksState;
    }
}
