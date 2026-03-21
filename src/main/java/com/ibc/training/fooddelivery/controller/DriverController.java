package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.request.DriverRequest;
import com.ibc.training.fooddelivery.response.DriverResponse;
import com.ibc.training.fooddelivery.response.OrderResponse;
import com.ibc.training.fooddelivery.service.DriverService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<ApiResponse<DriverResponse>> createDriver(
            @Valid @RequestBody DriverRequest request) {
        var dto = driverService.createDriver(request.toDTO());
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), "Driver created successfully", DriverResponse.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/drivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDrivers() {
        var dtos = driverService.getAllDrivers();
        var responses = dtos.stream().map(DriverResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Drivers retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<ApiResponse<DriverResponse>> getDriverById(
            @PathVariable Integer driverId) {
        var dto = driverService.getDriverById(driverId);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Driver retrieved successfully", DriverResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/drivers/{driverId}")
    public ResponseEntity<ApiResponse<String>> deleteDriver(
            @PathVariable Integer driverId) {
        try {
            driverService.deleteDriver(driverId);
            var response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Driver deleted successfully", "null");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            var response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Driver not found", "null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/orders/{orderId}/assignDriver/{driverId}")
    public ResponseEntity<ApiResponse<OrderResponse>> assignDriver(
            @PathVariable Integer orderId,
            @PathVariable Integer driverId) {
        var dto = driverService.assignDriverToOrder(orderId, driverId);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Driver assigned to order successfully", OrderResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/drivers/{driverId}/location")
    public ResponseEntity<ApiResponse<DriverResponse>> updateLocation(
            @PathVariable Integer driverId,
            @RequestParam String location) {
        var dto = driverService.updateDriverLocation(driverId, location);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Driver location updated successfully", DriverResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/drivers/{driverId}/orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getDriverOrders(
            @PathVariable Integer driverId) {
        var dtos = driverService.getOrdersByDriver(driverId);
        var responses = dtos.stream().map(OrderResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Driver orders retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }
}