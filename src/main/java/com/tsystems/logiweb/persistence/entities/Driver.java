package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the drivers database table.
 * 
 */
@Entity
@Table(name = "drivers")
@NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d")
public class Driver implements Serializable {
    /**
     * Utility serialization attribute.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * 
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 
     */
    @Column(name = "personal_number")
    private int personalNumber;

    /**
     * Bi-directional many-to-one association to DriverWorkSummary.
     */
    @OneToMany(mappedBy = "driver")
    private Set<DriverWorkSummary> driverWorkSummaries;

    /**
     * Bi-directional many-to-one association to DriverWorkSummary.
     */
    @OneToOne(mappedBy = "driver")
    private DriverState driverState;

    /**
     * Bi-directional many-to-one association to OrderToDriver.
     */
    @OneToMany(mappedBy = "driver")
    private Set<OrderToDriver> ordersToDrivers;

    /**
     * @return
     */
    public final int getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public final String getFirstName() {
        return this.firstName;
    }

    /**
     * @param name
     */
    public final void setFirstName(final String name) {
        this.firstName = name;
    }

    /**
     * @return
     */
    public final String getLastName() {
        return this.lastName;
    }

    /**
     * @param lastName
     */
    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public final int getPersonalNumber() {
        return this.personalNumber;
    }

    /**
     * @param personalNumber
     */
    public final void setPersonalNumber(final int personalNumber) {
        this.personalNumber = personalNumber;
    }

    /**
     * @return
     */
    public final Set<DriverWorkSummary> getDriverWorkSummaries() {
        return this.driverWorkSummaries;
    }

    /**
     * @param workSummaries
     */
    public final void setDriverWorkSummaries(
            final Set<DriverWorkSummary> workSummaries) {
        this.driverWorkSummaries = workSummaries;
    }

    /**
     * @param driverWorkSummary
     * @return
     */
    public final DriverWorkSummary addDriverWorkSummary(
            final DriverWorkSummary driverWorkSummary) {
        getDriverWorkSummaries().add(driverWorkSummary);
        driverWorkSummary.setDriver(this);

        return driverWorkSummary;
    }

    /**
     * @param driverWorkSummary
     * @return
     */
    public final DriverWorkSummary removeDriverWorkSummary(
            final DriverWorkSummary driverWorkSummary) {
        getDriverWorkSummaries().remove(driverWorkSummary);
        driverWorkSummary.setDriver(null);

        return driverWorkSummary;
    }

    /**
     * @return
     */
    public final DriverState getDriverState() {
        return this.driverState;
    }

    /**
     * @param state
     */
    public final void setDriverState(final DriverState state) {
        this.driverState = state;
    }

    /**
     * @return
     */
    public final Set<OrderToDriver> getOrdersToDrivers() {
        return this.ordersToDrivers;
    }

    /**
     * @param ordersToDriver
     */
    public final void setOrdersToDriver(final Set<OrderToDriver> ordersToDriver) {
        this.ordersToDrivers = ordersToDriver;
    }

    /**
     * @param orderToDriver
     * @return
     */
    public final OrderToDriver addOrderToDriver(
            final OrderToDriver orderToDriver) {
        getOrdersToDrivers().add(orderToDriver);
        orderToDriver.setDriver(this);

        return orderToDriver;
    }

    /**
     * @param orderToDriver
     * @return
     */
    public final OrderToDriver removeOrderToDriver(
            final OrderToDriver orderToDriver) {
        getOrdersToDrivers().remove(orderToDriver);
        orderToDriver.setDriver(null);

        return orderToDriver;
    }
}
