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
public class TruckCondition implements Serializable {
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

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String name;

    // bi-directional many-to-one association to TruckState
    /**
     *
     */
    @OneToMany(mappedBy = "truckCondition")
    private Set<TruckState> trucksStates;

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * @return
     */
    public TruckCondition setId(final int id) {
        this.id = id;
        return this;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     * @return
     */
    public TruckCondition setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * @return
     */
    public TruckCondition setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * @return
     */
    public Set<TruckState> getTrucksStates() {
        return trucksStates;
    }

    /**
     * @param trucksStates
     * @return
     */
    public TruckCondition setTrucksStates(final Set<TruckState> trucksStates) {
        this.trucksStates = trucksStates;
        return this;
    }

    /**
     * @param trucksState
     * @return
     */
    public TruckState addTrucksState(final TruckState trucksState) {
        getTrucksStates().add(trucksState);
        trucksState.setTruckCondition(this);

        return trucksState;
    }

    /**
     * @param trucksState
     * @return
     */
    public TruckState removeTrucksState(final TruckState trucksState) {
        getTrucksStates().remove(trucksState);
        trucksState.setTruckCondition(null);

        return trucksState;
    }
}
