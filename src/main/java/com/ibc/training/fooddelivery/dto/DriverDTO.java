package com.ibc.training.fooddelivery.dto;

import jakarta.validation.constraints.NotBlank;

public class DriverDTO {

    private Long id;

    @NotBlank(message = "Driver name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}