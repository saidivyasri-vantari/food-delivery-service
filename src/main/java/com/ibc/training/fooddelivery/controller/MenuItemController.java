package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    // ✅ GET all menu items for a restaurant
    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getMenuByRestaurant(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Menu items fetched successfully",
                        menuItemService.getMenuByRestaurant(restaurantId))
        );
    }

    // ✅ CREATE menu item for a restaurant
    @PostMapping
    public ResponseEntity<ApiResponse<MenuItemDTO>> createMenuItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuItemDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Menu item created successfully",
                        menuItemService.createMenuItem(restaurantId, dto)));
    }

    // ✅ GET menu item by ID
    @GetMapping("/{menuItemId}")
    public ResponseEntity<ApiResponse<MenuItemDTO>> getMenuItemById(
            @PathVariable Long restaurantId,
            @PathVariable Long menuItemId) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Menu item fetched successfully",
                        menuItemService.getMenuItem(menuItemId))
        );
    }

    // ✅ UPDATE menu item
    @PutMapping("/{menuItemId}")
    public ResponseEntity<ApiResponse<MenuItemDTO>> updateMenuItem(
            @PathVariable Long restaurantId,
            @PathVariable Long menuItemId,
            @Valid @RequestBody MenuItemDTO dto) {

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Menu item updated successfully",
                        menuItemService.updateMenuItem(menuItemId, dto))
        );
    }

    // ✅ DELETE menu item
    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteMenuItem(
            @PathVariable Long restaurantId,
            @PathVariable Long menuItemId) {

        menuItemService.deleteMenuItem(menuItemId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Menu item deleted successfully", null)
        );
    }
}