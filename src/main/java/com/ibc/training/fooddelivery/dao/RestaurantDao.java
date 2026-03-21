package com.ibc.training.fooddelivery.dao;


import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestaurantDao {

    private final RestaurantRepository repository;

    public RestaurantDao(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Optional<Restaurant> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}