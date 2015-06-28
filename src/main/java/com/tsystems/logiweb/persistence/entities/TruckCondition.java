package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the truck_conditions database table.
 * 
 */
@Entity
@Table(name = "truck_conditions")
@NamedQuery(name = "TruckCondition.findAll", query = "SELECT t FROM TruckCondition t")
public class TruckCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private String name;

    // bi-directional many-to-one association to TruckState
    @OneToMany(mappedBy = "truckCondition")
    private Set<TruckState> trucksStates;

    public TruckCondition() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TruckState> getTrucksStates() {
        return this.trucksStates;
    }

    public void setTrucksStates(Set<TruckState> trucksStates) {
        this.trucksStates = trucksStates;
    }

    public TruckState addTrucksState(TruckState trucksState) {
        getTrucksStates().add(trucksState);
        trucksState.setTruckCondition(this);

        return trucksState;
    }

    public TruckState removeTrucksState(TruckState trucksState) {
        getTrucksStates().remove(trucksState);
        trucksState.setTruckCondition(null);

        return trucksState;
    }
}
