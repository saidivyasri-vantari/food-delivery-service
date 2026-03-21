package com.ibc.training.fooddelivery.response;

import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.dto.DeliveryAddressDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;

import java.util.List;

public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;

    private List<OrderDTO> orders;

    private List<DeliveryAddressDTO> deliveryAddresses;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<OrderDTO> getOrders() { return orders; }
    public void setOrders(List<OrderDTO> orders) { this.orders = orders; }

    public List<DeliveryAddressDTO> getDeliveryAddresses() { return deliveryAddresses; }
    public void setDeliveryAddresses(List<DeliveryAddressDTO> deliveryAddresses) { this.deliveryAddresses = deliveryAddresses; }

    // --- Convert DTO → Response ---
    public static CustomerResponse fromDTO(CustomerDTO dto) {
        CustomerResponse response = new CustomerResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setEmail(dto.getEmail());
        response.setPhone(dto.getPhone());
        response.setOrders(dto.getOrders());
        response.setDeliveryAddresses(dto.getDeliveryAddresses());
        return response;
    }
}