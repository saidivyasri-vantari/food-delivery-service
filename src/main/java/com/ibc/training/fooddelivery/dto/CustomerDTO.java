package com.ibc.training.fooddelivery.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,15}", message = "Phone must be 10-15 digits")
    private String phone;

    private List<OrderDTO> orders;

    private List<DeliveryAddressDTO> deliveryAddresses;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<DeliveryAddressDTO> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<DeliveryAddressDTO> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }
}