package com.ibc.training.fooddelivery.dto;

import jakarta.validation.constraints.*;

public class RatingDTO {

    private Long id;

    @NotNull(message = "OrderId is required")
    private Long orderId;

    @NotNull(message = "RestaurantId is required")
    private Long restaurantId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating can be at most 5")
    private Integer rating;

    @Size(max = 1000, message = "Review can be at most 1000 characters")
    private String review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}