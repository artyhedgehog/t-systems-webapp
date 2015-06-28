package com.tsystems.logiweb.dao.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the cargos database table.
 * 
 */
@Entity
@Table(name = "cargos")
@NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
public class Cargo implements Serializable {

    /**
     * Serialization utility property.
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
    @Column(name = "cargo_number")
    private int cargoNumber;

    /**
     * 
     */
    @Column(name = "mass_kg")
    private float massKg;

    /**
     * 
     */
    private String title;

    /**
     * Bi-directional many-to-one association to CargoStatus.
     */
    @ManyToOne
    @JoinColumn(name = "cargo_status_id")
    private CargoStatus cargoStatus;

    /**
     * Bi-directional many-to-one association to OrderWaypoint.
     */
    @OneToMany(mappedBy = "cargo")
    private Set<OrderWaypoint> orderWaypoints;

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
    public final int getCargoNumber() {
        return this.cargoNumber;
    }

    /**
     * @param cargoNumber
     */
    public final void setCargoNumber(final int cargoNumber) {
        this.cargoNumber = cargoNumber;
    }

    /**
     * @return
     */
    public final float getMassKg() {
        return this.massKg;
    }

    /**
     * @param massKg
     */
    public final void setMassKg(final float massKg) {
        this.massKg = massKg;
    }

    /**
     * @return
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * @param title
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public final CargoStatus getCargoStatus() {
        return this.cargoStatus;
    }

    /**
     * @param cargoStatus
     */
    public final void setCargoStatus(final CargoStatus cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    /**
     * @return
     */
    public final Set<OrderWaypoint> getOrderWaypoints() {
        return this.orderWaypoints;
    }

    /**
     * @param waypoints
     */
    public final void setOrdersWaypoints(final Set<OrderWaypoint> waypoints) {
        this.orderWaypoints = waypoints;
    }

    /**
     * @param waypoint
     * @return
     */
    public final OrderWaypoint addOrdersWaypoint(final OrderWaypoint waypoint) {
        getOrderWaypoints().add(waypoint);
        waypoint.setCargo(this);

        return waypoint;
    }

    /**
     * @param waypoint
     * @return
     */
    public final OrderWaypoint removeOrdersWaypoint(final OrderWaypoint waypoint) {
        getOrderWaypoints().remove(waypoint);
        waypoint.setCargo(null);

        return waypoint;
    }
}
