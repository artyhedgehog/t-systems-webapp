package com.tsystems.logiweb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the driver_work_summary database table.
 * 
 */
@Entity
@Table(name = "driver_work_summary")
@NamedQuery(name = "DriverWorkSummary.findAll", query = "SELECT d FROM DriverWorkSummary d")
public class DriverWorkSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "hours_worked")
    private float hoursWorked;

    @Temporal(TemporalType.DATE)
    @Column(name = "period_end")
    private Date periodEnd;

    @Temporal(TemporalType.DATE)
    @Column(name = "period_start")
    private Date periodStart;

    // bi-directional many-to-one association to Driver
    @ManyToOne
    private Driver driver;

    public DriverWorkSummary() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getHoursWorked() {
        return this.hoursWorked;
    }

    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Date getPeriodEnd() {
        return this.periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Date getPeriodStart() {
        return this.periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
