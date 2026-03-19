package com.ibc.training.fooddelivery.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "delivery_areas")
public class DeliveryArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}