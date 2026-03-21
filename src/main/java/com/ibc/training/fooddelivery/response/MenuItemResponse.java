package com.ibc.training.fooddelivery.response;


import com.ibc.training.fooddelivery.dto.MenuItemDTO;

public class MenuItemResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long restaurantId;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    // --- Convert DTO → Response ---
    public static MenuItemResponse fromDTO(MenuItemDTO dto) {
        MenuItemResponse response = new MenuItemResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setPrice(dto.getPrice());
        response.setDescription(dto.getDescription());
        response.setRestaurantId(dto.getRestaurantId());
        return response;
    }
}