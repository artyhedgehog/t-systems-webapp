package com.tsystems.logiweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private byte completed;

    @Column(name = "order_number")
    private int orderNumber;

    // bi-directional many-to-one association to Truck
    @ManyToOne
    private Truck truck;

    // bi-directional many-to-one association to OrderToDriver
    @OneToMany(mappedBy = "order")
    private Set<OrderToDriver> ordersToDrivers;

    // bi-directional many-to-one association to OrderWaypoint
    @OneToMany(mappedBy = "order")
    private Set<OrderWaypoint> ordersWaypoints;

    public Order() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getCompleted() {
        return this.completed;
    }

    public void setCompleted(byte completed) {
        this.completed = completed;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Truck getTruck() {
        return this.truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Set<OrderToDriver> getOrdersToDrivers() {
        return this.ordersToDrivers;
    }

    public void setOrdersToDrivers(Set<OrderToDriver> ordersToDrivers) {
        this.ordersToDrivers = ordersToDrivers;
    }

    public OrderToDriver addOrdersToDriver(OrderToDriver ordersToDriver) {
        getOrdersToDrivers().add(ordersToDriver);
        ordersToDriver.setOrder(this);

        return ordersToDriver;
    }

    public OrderToDriver removeOrdersToDriver(OrderToDriver ordersToDriver) {
        getOrdersToDrivers().remove(ordersToDriver);
        ordersToDriver.setOrder(null);

        return ordersToDriver;
    }

    public Set<OrderWaypoint> getOrdersWaypoints() {
        return this.ordersWaypoints;
    }

    public void setOrdersWaypoints(Set<OrderWaypoint> ordersWaypoints) {
        this.ordersWaypoints = ordersWaypoints;
    }

    public OrderWaypoint addOrdersWaypoint(OrderWaypoint ordersWaypoint) {
        getOrdersWaypoints().add(ordersWaypoint);
        ordersWaypoint.setOrder(this);

        return ordersWaypoint;
    }

    public OrderWaypoint removeOrdersWaypoint(OrderWaypoint ordersWaypoint) {
        getOrdersWaypoints().remove(ordersWaypoint);
        ordersWaypoint.setOrder(null);

        return ordersWaypoint;
    }
}
