package com.tsystems.logiweb.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the cargos database table.
 * 
 */
@Entity
@Table(name = "cargos")
@NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cargo_number")
    private int cargoNumber;

    @Column(name = "mass_kg")
    private float massKg;

    private String title;

    // bi-directional many-to-one association to CargoStatus
    @ManyToOne
    @JoinColumn(name = "cargo_status_id")
    private CargoStatus cargoStatus;

    // bi-directional many-to-one association to OrderWaypoint
    @OneToMany(mappedBy = "cargo")
    private Set<OrderWaypoint> ordersWaypoints;

    public Cargo() {
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getCargoNumber() {
	return this.cargoNumber;
    }

    public void setCargoNumber(int cargoNumber) {
	this.cargoNumber = cargoNumber;
    }

    public float getMassKg() {
	return this.massKg;
    }

    public void setMassKg(float massKg) {
	this.massKg = massKg;
    }

    public String getTitle() {
	return this.title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public CargoStatus getCargoStatus() {
	return this.cargoStatus;
    }

    public void setCargoStatus(CargoStatus cargoStatus) {
	this.cargoStatus = cargoStatus;
    }

    public Set<OrderWaypoint> getOrdersWaypoints() {
	return this.ordersWaypoints;
    }

    public void setOrdersWaypoints(Set<OrderWaypoint> ordersWaypoints) {
	this.ordersWaypoints = ordersWaypoints;
    }

    public OrderWaypoint addOrdersWaypoint(OrderWaypoint ordersWaypoint) {
	getOrdersWaypoints().add(ordersWaypoint);
	ordersWaypoint.setCargo(this);

	return ordersWaypoint;
    }

    public OrderWaypoint removeOrdersWaypoint(OrderWaypoint ordersWaypoint) {
	getOrdersWaypoints().remove(ordersWaypoint);
	ordersWaypoint.setCargo(null);

	return ordersWaypoint;
    }
}
