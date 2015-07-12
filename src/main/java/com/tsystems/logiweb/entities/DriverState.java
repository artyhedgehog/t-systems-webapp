package com.tsystems.logiweb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the drivers_state database table.
 *
 */
@Entity
@Table(name = "drivers_state")
public class DriverState implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional one-to-one association to Driver
    @Id
    @OneToOne
    private Driver driver;

    // bi-directional many-to-one association to DriverStatus
    @ManyToOne
    @JoinColumn(name = "driver_status_id")
    private DriverStatus driverStatus;

    // bi-directional many-to-one association to Town
    @ManyToOne
    private Town town;

    // bi-directional many-to-one association to Truck
    @ManyToOne
    private Truck truck;

    public DriverState() {
    }

    public Driver getDriver() {
	return driver;
    }

    public void setDriver(final Driver driver) {
	this.driver = driver;
    }

    public DriverStatus getDriverStatus() {
	return driverStatus;
    }

    public void setDriverStatus(final DriverStatus driverStatus) {
	this.driverStatus = driverStatus;
    }

    public Town getTown() {
	return town;
    }

    public void setTown(final Town town) {
	this.town = town;
    }

    public Truck getTruck() {
	return truck;
    }

    public void setTruck(final Truck truck) {
	this.truck = truck;
    }

}