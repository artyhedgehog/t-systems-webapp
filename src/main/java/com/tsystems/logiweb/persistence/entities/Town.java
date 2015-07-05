package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the towns database table.
 *
 */
@Entity
@Table(name = "towns")
@NamedQuery(name = "Town.findAll", query = "SELECT t FROM Town t")
public class Town implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    // bi-directional many-to-one association to DriverState
    @OneToMany(mappedBy = "town")
    private Set<DriverState> driversStates;

    // bi-directional many-to-one association to OrderWaypoint
    @OneToMany(mappedBy = "town")
    private Set<OrderWaypoint> ordersWaypoints;

    // bi-directional many-to-one association to Road
    @OneToMany(mappedBy = "town1")
    private Set<Road> roads1;

    // bi-directional many-to-one association to Road
    @OneToMany(mappedBy = "town2")
    private Set<Road> roads2;

    // bi-directional many-to-one association to TruckState
    @OneToMany(mappedBy = "town")
    private Set<TruckState> trucksStates;

    public Town() {
    }

    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public Set<DriverState> getDriversStates() {
	return driversStates;
    }

    public void setDriversStates(final Set<DriverState> driversStates) {
	this.driversStates = driversStates;
    }

    public DriverState addDriversState(final DriverState driversState) {
	getDriversStates().add(driversState);
	driversState.setTown(this);

	return driversState;
    }

    public DriverState removeDriversState(final DriverState driversState) {
	getDriversStates().remove(driversState);
	driversState.setTown(null);

	return driversState;
    }

    public Set<OrderWaypoint> getOrdersWaypoints() {
	return ordersWaypoints;
    }

    public void setOrdersWaypoints(final Set<OrderWaypoint> ordersWaypoints) {
	this.ordersWaypoints = ordersWaypoints;
    }

    public OrderWaypoint addOrdersWaypoint(final OrderWaypoint ordersWaypoint) {
	getOrdersWaypoints().add(ordersWaypoint);
	ordersWaypoint.setTown(this);

	return ordersWaypoint;
    }

    public OrderWaypoint removeOrdersWaypoint(final OrderWaypoint ordersWaypoint) {
	getOrdersWaypoints().remove(ordersWaypoint);
	ordersWaypoint.setTown(null);

	return ordersWaypoint;
    }

    public Set<Road> getRoads1() {
	return roads1;
    }

    public void setRoads1(final Set<Road> roads1) {
	this.roads1 = roads1;
    }

    public Road addRoads1(final Road roads1) {
	getRoads1().add(roads1);
	roads1.setTown1(this);

	return roads1;
    }

    public Road removeRoads1(final Road roads1) {
	getRoads1().remove(roads1);
	roads1.setTown1(null);

	return roads1;
    }

    public Set<Road> getRoads2() {
	return roads2;
    }

    public void setRoads2(final Set<Road> roads2) {
	this.roads2 = roads2;
    }

    public Road addRoads2(final Road roads2) {
	getRoads2().add(roads2);
	roads2.setTown2(this);

	return roads2;
    }

    public Road removeRoads2(final Road roads2) {
	getRoads2().remove(roads2);
	roads2.setTown2(null);

	return roads2;
    }

    public Set<TruckState> getTrucksStates() {
	return trucksStates;
    }

    public void setTrucksStates(final Set<TruckState> trucksStates) {
	this.trucksStates = trucksStates;
    }

    public TruckState addTrucksState(final TruckState trucksState) {
	getTrucksStates().add(trucksState);
	trucksState.setTown(this);

	return trucksState;
    }

    public TruckState removeTrucksState(final TruckState trucksState) {
	getTrucksStates().remove(trucksState);
	trucksState.setTown(null);

	return trucksState;
    }

    @Override
    public String toString() {
        return getName();
    }
}
