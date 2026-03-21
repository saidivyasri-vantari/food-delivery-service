package com.ibc.training.fooddelivery.response;

import com.ibc.training.fooddelivery.dto.CouponDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.dto.OrderItemDTO;
import com.ibc.training.fooddelivery.dto.RatingDTO;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Integer id;
    private LocalDateTime orderDate;
    private Integer customerId;
    private Integer restaurantId;
    private Integer driverId;
    private String status;

    private List<OrderItemDTO> items;
    private List<CouponDTO> coupons;
    private List<RatingDTO> ratings;

    // --- Getters & Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Integer restaurantId) { this.restaurantId = restaurantId; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }

    public List<CouponDTO> getCoupons() { return coupons; }
    public void setCoupons(List<CouponDTO> coupons) { this.coupons = coupons; }

    public List<RatingDTO> getRatings() { return ratings; }
    public void setRatings(List<RatingDTO> ratings) { this.ratings = ratings; }

    // --- Convert DTO → Response ---
    public static OrderResponse fromDTO(OrderDTO dto) {
        OrderResponse response = new OrderResponse();
        response.setId(dto.getId());
        response.setOrderDate(dto.getOrderDate());
        response.setCustomerId(dto.getCustomerId());
        response.setRestaurantId(dto.getRestaurantId());
        response.setDriverId(dto.getDriverId());
        response.setStatus(dto.getStatus());
        response.setItems(dto.getItems());
        response.setCoupons(dto.getCoupons());
        response.setRatings(dto.getRatings());
        return response;
    }
}
