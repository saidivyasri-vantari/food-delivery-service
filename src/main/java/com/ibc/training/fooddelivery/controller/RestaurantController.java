package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.dto.*;
import com.ibc.training.fooddelivery.service.DeliveryAreaService;
import com.ibc.training.fooddelivery.service.MenuItemService;
import com.ibc.training.fooddelivery.service.RestaurantService;
import com.ibc.training.fooddelivery.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final ReviewService reviewService;
    private final DeliveryAreaService deliveryAreaService;

    public RestaurantController(RestaurantService restaurantService, MenuItemService menuItemService, ReviewService reviewService, DeliveryAreaService deliveryAreaService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
        this.reviewService = reviewService;
        this.deliveryAreaService = deliveryAreaService;
    }

    // ✅ GET all restaurants
    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getAllRestaurants() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Restaurants fetched successfully",
                        restaurantService.getAllRestaurants())
        );
    }

    // ✅ GET restaurant by ID
    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantDTO>> getRestaurantById(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Restaurant details fetched successfully",
                        restaurantService.getRestaurantById(restaurantId))
        );
    }

    // ✅ CREATE restaurant
    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantDTO>> createRestaurant(
            @Valid @RequestBody RestaurantDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Restaurant created successfully",
                        restaurantService.createRestaurant(dto)));
    }

    // ✅ UPDATE restaurant
    @PutMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantDTO>> updateRestaurant(
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantDTO dto) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Restaurant updated successfully",
                        restaurantService.updateRestaurant(restaurantId, dto))
        );
    }

    // ✅ DELETE restaurant
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<Void>> deleteRestaurant(
            @PathVariable Long restaurantId) {

        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Restaurant deleted successfully", null)
        );
    }

    // ✅ GET menu items of restaurant
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getMenuItems(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Menu items fetched successfully",
                        menuItemService.getMenuByRestaurant(restaurantId))
        );
    }

    // ✅ GET reviews of restaurant
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<ApiResponse<List<ReviewDTO>>> getReviews(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Reviews fetched successfully",
                        reviewService.getByRestaurant(restaurantId))
        );
    }

    // ✅ GET delivery areas of restaurant
    @GetMapping("/{restaurantId}/delivery-areas")
    public ResponseEntity<ApiResponse<List<DeliveryAreaDTO>>> getDeliveryAreas(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Delivery areas fetched successfully",
                        deliveryAreaService.getByRestaurant(restaurantId))
        );
    }
}