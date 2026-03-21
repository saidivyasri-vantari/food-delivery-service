package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.common.ApiResponse;
import com.ibc.training.fooddelivery.request.MenuItemRequest;
import com.ibc.training.fooddelivery.response.MenuItemResponse;
import com.ibc.training.fooddelivery.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemResponse>> getMenuItemById(@PathVariable Integer id) {
        var dto = menuItemService.getMenuItemById(id);
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Menu item retrieved successfully", MenuItemResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ApiResponse<List<MenuItemResponse>>> getMenuItemsByRestaurant(@PathVariable Integer restaurantId) {
        var dtos = menuItemService.getMenuItemsByRestaurant(restaurantId);
        var responses = dtos.stream().map(MenuItemResponse::fromDTO).collect(Collectors.toList());
        var apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Menu items retrieved successfully", responses);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MenuItemResponse>> createMenuItem(@Valid @RequestBody MenuItemRequest request) {
        var dto = menuItemService.createMenuItem(request.toDTO());
        var response = new ApiResponse<>(HttpStatus.CREATED.value(), "Menu item created successfully", MenuItemResponse.fromDTO(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemResponse>> updateMenuItem(@PathVariable Integer id,
                                                           @Valid @RequestBody MenuItemRequest request) {
        var dto = menuItemService.updateMenuItem(id, request.toDTO());
        var response = new ApiResponse<>(HttpStatus.OK.value(), "Menu item updated successfully", MenuItemResponse.fromDTO(dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenuItem(@PathVariable Integer id) {
        try {
            menuItemService.deleteMenuItem(id);
            var response = new ApiResponse<Void>(HttpStatus.NO_CONTENT.value(), "Menu item deleted successfully", null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (Exception e) {
            var response = new ApiResponse<Void>(HttpStatus.NOT_FOUND.value(), "Menu item not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}