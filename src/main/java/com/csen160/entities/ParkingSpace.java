package com.csen160.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_space")
public class ParkingSpace implements Serializable {
    private static final long serialVersionUID = 9200837707603204375L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int parking_id;

    @OneToOne
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private String lot;
    private String location;

    public int getParkingId() {
        return parking_id;
    }

    public void setParkingId(int parkingId) {
        this.parking_id = parkingId;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ParkingSpace() {
        super();
    }

    public ParkingSpace(String lot, String location) {
        super();
        this.lot = lot;
        this.location = location;
    }
}