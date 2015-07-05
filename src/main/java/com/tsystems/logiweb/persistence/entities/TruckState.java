package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

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

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "truck_id")
    private int truckId;

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
    @OneToOne
    private Truck truck;

    // bi-directional many-to-one association to TruckCondition
    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "truck_condition_id")
    private TruckCondition condition;

    /**
     * @return
     */
    public int getTruckId() {
	return truckId;
    }

    /**
     * @param truckId
     * @return
     */
    public TruckState setTruckId(final int truckId) {
	this.truckId = truckId;
	return this;
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
