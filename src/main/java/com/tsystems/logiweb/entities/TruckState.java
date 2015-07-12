package com.tsystems.logiweb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the trucks_state database table.
 *
 */
@Entity
@Table(name = "trucks_state")
public class TruckState implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to Town
    /**
     *
     */
    @ManyToOne
    private Town town;

    // bi-directional one-to-one association to Truck
    /**
     *
     */
    @Id
    @OneToOne(fetch = FetchType.EAGER)
    private Truck truck;

    // bi-directional many-to-one association to TruckCondition
    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_condition_id")
    private TruckCondition condition;

    /**
     * @return
     */
    public int getTruckId() {
	return truck.getId();
    }

    /**
     * @return
     */
    public Town getTown() {
	return town;
    }

    /**
     * @param town
     * @return
     */
    public TruckState setTown(final Town town) {
	this.town = town;
	return this;
    }

    /**
     * @return
     */
    public Truck getTruck() {
	return truck;
    }

    /**
     * @param truck
     * @return
     */
    public TruckState setTruck(final Truck truck) {
	this.truck = truck;
	return this;
    }

    /**
     * @return
     */
    public TruckCondition getCondition() {
	return condition;
    }

    /**
     * @param condition
     * @return
     */
    public TruckState setCondition(final TruckCondition condition) {
	this.condition = condition;
	return this;
    }
}
