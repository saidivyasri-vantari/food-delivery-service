package com.ibc.training.fooddelivery.controller;


import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // ✅ CREATE Customer
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> createCustomer(
            @Valid @RequestBody CustomerDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Customer created successfully",
                        customerService.createCustomer(dto)));
    }

    // ✅ GET Customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Customer fetched successfully",
                        customerService.getCustomer(customerId))
        );
    }

    // ✅ GET All Customers
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomers() {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Customers fetched successfully",
                        customerService.getAllCustomers())
        );
    }

    // ✅ UPDATE Customer
    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerDTO dto) {

        CustomerDTO updated = customerService.updateCustomer(customerId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Customer updated successfully", updated)
        );
    }

    // ✅ DELETE Customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Customer deleted successfully", null)
        );
    }
}