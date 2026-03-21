package com.ibc.training.fooddelivery.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "orders_coupons")
@IdClass(OrdersCoupons.OrdersCouponsId.class)
public class OrdersCoupons {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public static class OrdersCouponsId implements Serializable {
        private Integer order;
        private Integer coupon;

        public OrdersCouponsId() {}
        public OrdersCouponsId(Integer order, Integer coupon) {
            this.order = order;
            this.coupon = coupon;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrdersCouponsId)) return false;
            OrdersCouponsId that = (OrdersCouponsId) o;
            return Objects.equals(order, that.order) &&
                    Objects.equals(coupon, that.coupon);
        }

        @Override
        public int hashCode() { return Objects.hash(order, coupon); }
    }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Coupon getCoupon() { return coupon; }
    public void setCoupon(Coupon coupon) { this.coupon = coupon; }
}