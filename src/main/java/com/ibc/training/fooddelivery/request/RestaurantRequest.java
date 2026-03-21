package com.ibc.training.fooddelivery.request;

import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RestaurantRequest {

    @NotBlank(message = "Restaurant name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 500)
    private String address;

    @Pattern(regexp = "\\d{10,15}", message = "Phone must be 10-15 digits")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // --- Convert Request → DTO ---
    public RestaurantDTO toDTO() {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setName(this.name);
        dto.setAddress(this.address);
        dto.setPhone(this.phone);
        return dto;
    }
}
