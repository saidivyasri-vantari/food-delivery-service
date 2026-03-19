package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.repository.MenuItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenuItemDao {

    private final MenuItemRepository repository;

    public MenuItemDao(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem save(MenuItem item) {
        return repository.save(item);
    }

    public Optional<MenuItem> findById(Long id) {
        return repository.findById(id);
    }

    public List<MenuItem> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public List<MenuItem> findByRestaurant(Restaurant restaurant){
        return repository.findByRestaurant(restaurant);
    }
}