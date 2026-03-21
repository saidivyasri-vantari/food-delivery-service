package com.ibc.training.fooddelivery.response;

import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.dto.RatingDTO;

import java.util.List;

public class RestaurantResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;

    private List<MenuItemDTO> menuItems;
    private List<OrderDTO> orders;
    private List<RatingDTO> ratings;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<MenuItemDTO> getMenuItems() { return menuItems; }
    public void setMenuItems(List<MenuItemDTO> menuItems) { this.menuItems = menuItems; }

    public List<OrderDTO> getOrders() { return orders; }
    public void setOrders(List<OrderDTO> orders) { this.orders = orders; }

    public List<RatingDTO> getRatings() { return ratings; }
    public void setRatings(List<RatingDTO> ratings) { this.ratings = ratings; }

    // --- Convert DTO → Response ---
    public static RestaurantResponse fromDTO(RestaurantDTO dto) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setAddress(dto.getAddress());
        response.setPhone(dto.getPhone());
        response.setMenuItems(dto.getMenuItems());
        response.setOrders(dto.getOrders());
        response.setRatings(dto.getRatings());
        return response;
    }
}
