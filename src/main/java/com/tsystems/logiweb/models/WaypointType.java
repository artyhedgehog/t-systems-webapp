package com.tsystems.logiweb.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the waypoint_types database table.
 * 
 */
@Entity
@Table(name = "waypoint_types")
@NamedQuery(name = "WaypointType.findAll", query = "SELECT w FROM WaypointType w")
public class WaypointType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private String name;

    // bi-directional many-to-one association to OrderWaypoint
    @OneToMany(mappedBy = "waypointType")
    private Set<OrderWaypoint> ordersWaypoints;

    public WaypointType() {
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

    public Set<OrderWaypoint> getOrdersWaypoints() {
        return this.ordersWaypoints;
    }

    public void setOrdersWaypoints(Set<OrderWaypoint> ordersWaypoints) {
        this.ordersWaypoints = ordersWaypoints;
    }

    public OrderWaypoint addOrdersWaypoint(OrderWaypoint ordersWaypoint) {
        getOrdersWaypoints().add(ordersWaypoint);
        ordersWaypoint.setWaypointType(this);

        return ordersWaypoint;
    }

    public OrderWaypoint removeOrdersWaypoint(OrderWaypoint ordersWaypoint) {
        getOrdersWaypoints().remove(ordersWaypoint);
        ordersWaypoint.setWaypointType(null);

        return ordersWaypoint;
    }
}
