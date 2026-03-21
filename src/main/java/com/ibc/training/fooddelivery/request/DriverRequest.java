package com.ibc.training.fooddelivery.request;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import jakarta.validation.constraints.*;

public class DriverRequest {

    @NotBlank(message = "Driver name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10,15}", message = "Phone must be 10-15 digits")
    private String phone;

    @NotBlank(message = "Vehicle info is required")
    private String vehicle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    // --- Convert Request → DTO ---
    public DriverDTO toDTO() {
        DriverDTO dto = new DriverDTO();
        dto.setName(this.name);
        dto.setPhone(this.phone);
        dto.setVehicle(this.vehicle);
        return dto;
    }
}
