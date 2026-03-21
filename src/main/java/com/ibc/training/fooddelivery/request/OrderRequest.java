package com.ibc.training.fooddelivery.request;

import com.ibc.training.fooddelivery.dto.CouponDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.dto.OrderItemDTO;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

    @NotNull(message = "CustomerId is required")
    private Integer customerId;

    @NotNull(message = "RestaurantId is required")
    private Integer restaurantId;

    @NotBlank(message = "Order status is required")
    private String status;

    private List<OrderItemDTO> items;
    private List<CouponDTO> coupons;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public List<CouponDTO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponDTO> coupons) {
        this.coupons = coupons;
    }

    // --- Convert Request → DTO ---
    public OrderDTO toDTO() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderDate(this.orderDate);
        dto.setCustomerId(this.customerId);
        dto.setRestaurantId(this.restaurantId);
        dto.setStatus(this.status);
        dto.setItems(this.items);
        dto.setCoupons(this.coupons);
        return dto;
    }
}
