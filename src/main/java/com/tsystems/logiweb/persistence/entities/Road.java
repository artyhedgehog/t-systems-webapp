package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the roads database table.
 * 
 */
@Entity
@Table(name = "roads")
@NamedQuery(name = "Road.findAll", query = "SELECT r FROM Road r")
public class Road implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "distance_km")
    private float distanceKm;

    // bi-directional many-to-one association to Town
    @ManyToOne
    @JoinColumn(name = "first_town_id")
    private Town town1;

    // bi-directional many-to-one association to Town
    @ManyToOne
    @JoinColumn(name = "second_town_id")
    private Town town2;

    public Road() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistanceKm() {
        return this.distanceKm;
    }

    public void setDistanceKm(float distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Town getTown1() {
        return this.town1;
    }

    public void setTown1(Town town1) {
        this.town1 = town1;
    }

    public Town getTown2() {
        return this.town2;
    }

    public void setTown2(Town town2) {
        this.town2 = town2;
    }
}
