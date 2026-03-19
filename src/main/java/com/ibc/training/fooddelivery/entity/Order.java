package com.ibc.training.fooddelivery.entity;

import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.entity.OrderItem;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderTime;       // ✅ getter & setter missing before
    private BigDecimal totalAmount;
    private String status;
    private String deliveryAddress;       // ✅ getter & setter missing before

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LocalDateTime getOrderTime() { return orderTime; }    // ✅ added
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }  // ✅ added

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDeliveryAddress() { return deliveryAddress; } // ✅ added
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; } // ✅ added

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}