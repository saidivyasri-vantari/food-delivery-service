package com.ibc.training.fooddelivery.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponDTO {
    private Long id;

    @NotBlank(message = "Coupon code is required")
    private String couponCode;

    @NotNull(message = "Discount amount is required")
    @Positive(message = "Discount must be positive")
    private BigDecimal discountAmount;

    @FutureOrPresent(message = "Expiry date must be today or in the future")
    private LocalDate expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}