package com.ibc.training.fooddelivery.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer id;

    @Column(name = "driver_name", nullable = false)
    private String name;

    @Column(name = "driver_phone")
    private String phone;

    @Column(name = "driver_vehicle")
    private String vehicle;

    @OneToMany(mappedBy = "driver")
    private List<Order> orders;

    // Getters and setters
    public Integer  getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}