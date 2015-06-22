package com.tsystems.logiweb.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the orders_waypoints database table.
 * 
 */
@Entity
@Table(name = "orders_waypoints")
@NamedQuery(name = "OrderWaypoint.findAll", query = "SELECT o FROM OrderWaypoint o")
public class OrderWaypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // bi-directional many-to-one association to Cargo
    @ManyToOne
    private Cargo cargo;

    // bi-directional many-to-one association to Order
    @ManyToOne
    private Order order;

    // bi-directional many-to-one association to Town
    @ManyToOne
    private Town town;

    // bi-directional many-to-one association to WaypointType
    @ManyToOne
    @JoinColumn(name = "waypoint_type_id")
    private WaypointType waypointType;

    public OrderWaypoint() {
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Cargo getCargo() {
	return this.cargo;
    }

    public void setCargo(Cargo cargo) {
	this.cargo = cargo;
    }

    public Order getOrder() {
	return this.order;
    }

    public void setOrder(Order order) {
	this.order = order;
    }

    public Town getTown() {
	return this.town;
    }

    public void setTown(Town town) {
	this.town = town;
    }

    public WaypointType getWaypointType() {
	return this.waypointType;
    }

    public void setWaypointType(WaypointType waypointType) {
	this.waypointType = waypointType;
    }
}
