package com.tsystems.logiweb.dao.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the drivers_state database table.
 * 
 */
@Entity
@Table(name = "drivers_state")
@NamedQuery(name = "DriverState.findAll", query = "SELECT d FROM DriverState d")
public class DriverState implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_id")
    private int driverId;

    // bi-directional one-to-one association to Driver
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

    public int getDriverId() {
	return this.driverId;
    }

    public void setDriverId(int driverId) {
	this.driverId = driverId;
    }

    public Driver getDriver() {
	return this.driver;
    }

    public void setDriver(Driver driver) {
	this.driver = driver;
    }

    public DriverStatus getDriverStatus() {
	return this.driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
	this.driverStatus = driverStatus;
    }

    public Town getTown() {
	return this.town;
    }

    public void setTown(Town town) {
	this.town = town;
    }

    public Truck getTruck() {
	return this.truck;
    }

    public void setTruck(Truck truck) {
	this.truck = truck;
    }

}