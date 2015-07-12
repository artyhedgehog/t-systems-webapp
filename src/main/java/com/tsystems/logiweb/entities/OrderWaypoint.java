package com.tsystems.logiweb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the orders_waypoints database table.
 * 
 */
@Entity
@Table(name = "orders_waypoints")
@NamedQuery(name = "OrderWaypoint.findAll", query = "SELECT o FROM OrderWaypoint o")
public class OrderWaypoint implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // bi-directional many-to-one association to Cargo
    /**
     * 
     */
    @ManyToOne
    private Cargo cargo;

    // bi-directional many-to-one association to Order
    /**
     * 
     */
    @ManyToOne
    private Order order;

    // bi-directional many-to-one association to Town
    /**
     * 
     */
    @ManyToOne
    private Town town;

    // bi-directional many-to-one association to WaypointType
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "waypoint_type_id")
    private WaypointType waypointType;

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
    public final Cargo getCargo() {
        return this.cargo;
    }

    /**
     * @param cargo
     */
    public final void setCargo(final Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return
     */
    public final Order getOrder() {
        return this.order;
    }

    /**
     * @param order
     */
    public final void setOrder(final Order order) {
        this.order = order;
    }

    /**
     * @return
     */
    public final Town getTown() {
        return this.town;
    }

    /**
     * @param town
     */
    public final void setTown(final Town town) {
        this.town = town;
    }

    /**
     * @return
     */
    public final WaypointType getWaypointType() {
        return this.waypointType;
    }

    /**
     * @param waypointType
     */
    public final void setWaypointType(final WaypointType waypointType) {
        this.waypointType = waypointType;
    }
}
