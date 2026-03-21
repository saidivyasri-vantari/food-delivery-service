package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.request.CustomerRequest;
import com.ibc.training.fooddelivery.response.CustomerResponse;
import com.ibc.training.fooddelivery.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
        var dto = customerService.createCustomer(request.toDTO());
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), "Customer created successfully", CustomerResponse.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Integer id) {
        var dto = customerService.getCustomerById(id);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Customer retrieved successfully", CustomerResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {
        var dtos = customerService.getAllCustomers();
        var responses = dtos.stream().map(CustomerResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Customers retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerRequest request) {
        var dto = customerService.updateCustomer(id, request.toDTO());
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Customer updated successfully", CustomerResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        var response = new ApiResponse<Void>(HttpStatus.NO_CONTENT.value(), "Customer deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}