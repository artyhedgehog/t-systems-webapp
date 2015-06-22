package com.tsystems.logiweb.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the trucks_state database table.
 * 
 */
@Entity
@Table(name = "trucks_state")
@NamedQuery(name = "TruckState.findAll", query = "SELECT t FROM TruckState t")
public class TruckState implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "truck_id")
    private int truckId;

    // bi-directional many-to-one association to Town
    @ManyToOne
    private Town town;

    // bi-directional one-to-one association to Truck
    @OneToOne
    private Truck truck;

    // bi-directional many-to-one association to TruckCondition
    @ManyToOne
    @JoinColumn(name = "truck_condition_id")
    private TruckCondition truckCondition;

    public TruckState() {
    }

    public int getTruckId() {
	return this.truckId;
    }

    public void setTruckId(int truckId) {
	this.truckId = truckId;
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

    public TruckCondition getTruckCondition() {
	return this.truckCondition;
    }

    public void setTruckCondition(TruckCondition truckCondition) {
	this.truckCondition = truckCondition;
    }
}
