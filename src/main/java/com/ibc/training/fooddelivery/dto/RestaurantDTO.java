package com.ibc.training.fooddelivery.dto;


import jakarta.validation.constraints.*;
import java.util.List;

public class RestaurantDTO {
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 500)
    private String address;

    @Pattern(regexp = "\\d{10,15}", message = "Phone must be 10-15 digits")
    private String phone;

    /*@NotBlank(message = "Cuisine type is required")
    @Size(max = 100)
    private String cuisineType;*/

    private List<MenuItemDTO> menuItems;
    private List<OrderDTO> orders;
    private List<RatingDTO> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }
}