package com.tsystems.logiweb.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the drivers database table.
 * 
 */
@Entity
@Table(name="drivers")
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="personal_number")
	private int personalNumber;

	//bi-directional many-to-one association to DriverWorkSummary
	@OneToMany(mappedBy="driver")
	private Set<DriverWorkSummary> driverWorkSummaries;

	//bi-directional one-to-one association to DriverState
	@OneToOne(mappedBy="driver")
	private DriverState driversState;

	//bi-directional many-to-one association to OrderToDriver
	@OneToMany(mappedBy="driver")
	private Set<OrderToDriver> ordersToDrivers;

	public Driver() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonalNumber() {
		return this.personalNumber;
	}

	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}

	public Set<DriverWorkSummary> getDriverWorkSummaries() {
		return this.driverWorkSummaries;
	}

	public void setDriverWorkSummaries(Set<DriverWorkSummary> driverWorkSummaries) {
		this.driverWorkSummaries = driverWorkSummaries;
	}

	public DriverWorkSummary addDriverWorkSummary(DriverWorkSummary driverWorkSummary) {
		getDriverWorkSummaries().add(driverWorkSummary);
		driverWorkSummary.setDriver(this);

		return driverWorkSummary;
	}

	public DriverWorkSummary removeDriverWorkSummary(DriverWorkSummary driverWorkSummary) {
		getDriverWorkSummaries().remove(driverWorkSummary);
		driverWorkSummary.setDriver(null);

		return driverWorkSummary;
	}

	public DriverState getDriversState() {
		return this.driversState;
	}

	public void setDriversState(DriverState driversState) {
		this.driversState = driversState;
	}

	public Set<OrderToDriver> getOrdersToDrivers() {
		return this.ordersToDrivers;
	}

	public void setOrdersToDrivers(Set<OrderToDriver> ordersToDrivers) {
		this.ordersToDrivers = ordersToDrivers;
	}

	public OrderToDriver addOrdersToDriver(OrderToDriver ordersToDriver) {
		getOrdersToDrivers().add(ordersToDriver);
		ordersToDriver.setDriver(this);

		return ordersToDriver;
	}

	public OrderToDriver removeOrdersToDriver(OrderToDriver ordersToDriver) {
		getOrdersToDrivers().remove(ordersToDriver);
		ordersToDriver.setDriver(null);

		return ordersToDriver;
	}

}