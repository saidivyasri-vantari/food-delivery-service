package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDao {

    private final OrderRepository repository;

    public OrderDao(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public List<Order> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Order> findByDriverId(Long driverId) {
        return repository.findByDriverId(driverId);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}