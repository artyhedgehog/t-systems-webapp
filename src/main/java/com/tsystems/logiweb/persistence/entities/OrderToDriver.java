package com.tsystems.logiweb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the orders_to_drivers database table.
 * 
 */
@Entity
@Table(name = "orders_to_drivers")
@NamedQuery(name = "OrderToDriver.findAll", query = "SELECT o FROM OrderToDriver o")
public class OrderToDriver implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // bi-directional many-to-one association to Driver
    @ManyToOne
    private Driver driver;

    // bi-directional many-to-one association to Order
    @ManyToOne
    private Order order;

    public OrderToDriver() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
