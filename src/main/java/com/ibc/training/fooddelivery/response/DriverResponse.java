package com.ibc.training.fooddelivery.response;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;

import java.util.List;

public class DriverResponse {

    private Long id;
    private String name;
    private String phone;
    private String vehicle;

    private List<OrderDTO> orders;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public List<OrderDTO> getOrders() { return orders; }
    public void setOrders(List<OrderDTO> orders) { this.orders = orders; }

    // --- Convert DTO → Response ---
    public static DriverResponse fromDTO(DriverDTO dto) {
        DriverResponse response = new DriverResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setPhone(dto.getPhone());
        response.setVehicle(dto.getVehicle());
        response.setOrders(dto.getOrders());
        return response;
    }
}
