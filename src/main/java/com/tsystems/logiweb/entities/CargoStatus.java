package com.tsystems.logiweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the cargo_statuses database table.
 * 
 */
@Entity
@Table(name = "cargo_statuses")
@NamedQuery(name = "CargoStatus.findAll", query = "SELECT c FROM CargoStatus c")
public class CargoStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private String name;

    // bi-directional many-to-one association to Cargo
    @OneToMany(mappedBy = "cargoStatus")
    private Set<Cargo> cargos;

    public CargoStatus() {
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

    public Set<Cargo> getCargos() {
	return this.cargos;
    }

    public void setCargos(Set<Cargo> cargos) {
	this.cargos = cargos;
    }

    public Cargo addCargo(Cargo cargo) {
	getCargos().add(cargo);
	cargo.setCargoStatus(this);

	return cargo;
    }

    public Cargo removeCargo(Cargo cargo) {
	getCargos().remove(cargo);
	cargo.setCargoStatus(null);

	return cargo;
    }

}