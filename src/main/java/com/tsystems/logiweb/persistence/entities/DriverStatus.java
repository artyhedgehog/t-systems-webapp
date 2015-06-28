package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the driver_statuses database table.
 * 
 */
@Entity
@Table(name = "driver_statuses")
@NamedQuery(name = "DriverStatus.findAll", query = "SELECT d FROM DriverStatus d")
public class DriverStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private String name;

    // bi-directional many-to-one association to DriverState
    @OneToMany(mappedBy = "driverStatus")
    private Set<DriverState> driversStates;

    public DriverStatus() {
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

    public Set<DriverState> getDriversStates() {
        return this.driversStates;
    }

    public void setDriversStates(Set<DriverState> driversStates) {
        this.driversStates = driversStates;
    }

    public DriverState addDriversState(DriverState driversState) {
        getDriversStates().add(driversState);
        driversState.setDriverStatus(this);

        return driversState;
    }

    public DriverState removeDriversState(DriverState driversState) {
        getDriversStates().remove(driversState);
        driversState.setDriverStatus(null);

        return driversState;
    }
}
