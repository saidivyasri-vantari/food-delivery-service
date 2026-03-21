package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.request.OrderRequest;
import com.ibc.training.fooddelivery.response.OrderResponse;
import com.ibc.training.fooddelivery.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {
        var dto = orderService.createOrder(orderRequest.toDTO());
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), "Order created successfully", OrderResponse.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(
            @PathVariable Integer id,
            @Valid @RequestBody OrderRequest orderRequest) {
        var dto = orderService.updateOrder(id, orderRequest.toDTO());
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Order updated successfully", OrderResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrder(id);
            var response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Order deleted successfully", "null");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            var response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Order not found", "null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Integer id) {
        var dto = orderService.getOrderById(id);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Order retrieved successfully", OrderResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        var dtos = orderService.getAllOrders();
        var responses = dtos.stream().map(OrderResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Orders retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrdersByCustomer(
            @PathVariable Integer customerId) {
        var dtos = orderService.getOrdersByCustomer(customerId);
        var responses = dtos.stream().map(OrderResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Customer orders retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }
}
