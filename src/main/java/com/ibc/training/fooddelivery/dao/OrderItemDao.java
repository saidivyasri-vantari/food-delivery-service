package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.OrderItem;
import com.ibc.training.fooddelivery.repository.OrderItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemDao {

    private final OrderItemRepository repository;

    public OrderItemDao(OrderItemRepository repository) {
        this.repository = repository;
    }

    public OrderItem save(OrderItem item) {
        return repository.save(item);
    }

    public List<OrderItem> findByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId);
    }
}