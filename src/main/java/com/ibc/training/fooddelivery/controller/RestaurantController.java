package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.dto.RatingDTO;
import com.ibc.training.fooddelivery.request.RestaurantRequest;
import com.ibc.training.fooddelivery.response.MenuItemResponse;
import com.ibc.training.fooddelivery.response.RestaurantResponse;
import com.ibc.training.fooddelivery.service.RestaurantService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // ✅ Create
    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantResponse>> createRestaurant(
            @Valid @RequestBody RestaurantRequest request) {
        var dto = restaurantService.createRestaurant(request.toDTO());
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), "Restaurant created successfully", RestaurantResponse.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ Get All
    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantResponse>>> getAllRestaurants() {
        var dtos = restaurantService.getAllRestaurants();
        var responses = dtos.stream().map(RestaurantResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Restaurants retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    // ✅ Get By ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantResponse>> getRestaurantById(
            @PathVariable Integer restaurantId) {
        var dto = restaurantService.getRestaurantById(restaurantId);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Restaurant retrieved successfully", RestaurantResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    // ✅ Update
    @PutMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantResponse>> updateRestaurant(
            @PathVariable Integer restaurantId,
            @Valid @RequestBody RestaurantRequest request) {
        var dto = restaurantService.updateRestaurant(restaurantId, request.toDTO());
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Restaurant updated successfully", RestaurantResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    // ✅ Delete
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<String>> deleteRestaurant(
            @PathVariable Integer restaurantId) {
        try {
            restaurantService.deleteRestaurant(restaurantId);
            var response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Restaurant deleted successfully", "null");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            var response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Restaurant not found", "null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // ============================
    // ✅ CHILD RESOURCES
    // ============================

    // 🍔 Menu Items
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<ApiResponse<List<MenuItemResponse>>> getMenuByRestaurant(
            @PathVariable Integer restaurantId) {
        var dtos = restaurantService.getMenuByRestaurant(restaurantId);
        var responses = dtos.stream().map(MenuItemResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Menu items retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    // ⭐ Reviews
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<ApiResponse<List<RatingDTO>>> getReviewsByRestaurant(
            @PathVariable Integer restaurantId) {
        var dtos = restaurantService.getReviewsByRestaurant(restaurantId);
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Reviews retrieved successfully", dtos);
        return ResponseEntity.ok(apiResponse);
    }
}