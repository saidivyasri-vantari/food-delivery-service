package com.ibc.training.fooddelivery.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Integer id;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    @NotNull(message = "CustomerId is required")
    private Integer customerId;

    @NotNull(message = "RestaurantId is required")
    private Integer restaurantId;

    private Integer driverId;  // driver can be assigned later

    @NotBlank(message = "Order status is required")
    private String status;

    private List<OrderItemDTO> items;
    private List<CouponDTO> coupons;
    private List<RatingDTO> ratings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<CouponDTO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponDTO> coupons) {
        this.coupons = coupons;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}