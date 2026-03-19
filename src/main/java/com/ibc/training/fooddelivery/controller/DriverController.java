package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    // ✅ CREATE Driver
    @PostMapping
    public ResponseEntity<ApiResponse<DriverDTO>> createDriver(
            @Valid @RequestBody DriverDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Driver created successfully",
                        driverService.create(dto)));
    }

    // ✅ GET Driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<ApiResponse<DriverDTO>> getDriverById(
            @PathVariable Long driverId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Driver fetched successfully",
                        driverService.getDriver(driverId))
        );
    }

    // ✅ GET All Drivers
    @GetMapping
    public ResponseEntity<ApiResponse<List<DriverDTO>>> getAllDrivers() {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Drivers fetched successfully",
                        driverService.getAll())
        );
    }

    // ✅ UPDATE Driver
    @PutMapping("/{driverId}")
    public ResponseEntity<ApiResponse<DriverDTO>> updateDriver(
            @PathVariable Long driverId,
            @Valid @RequestBody DriverDTO dto) {

        DriverDTO updated = driverService.updateDriver(driverId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Driver updated successfully", updated)
        );
    }

    // ✅ DELETE Driver
    @DeleteMapping("/{driverId}")
    public ResponseEntity<ApiResponse<Void>> deleteDriver(
            @PathVariable Long driverId) {

        driverService.deleteDriver(driverId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Driver deleted successfully", null)
        );
    }
}