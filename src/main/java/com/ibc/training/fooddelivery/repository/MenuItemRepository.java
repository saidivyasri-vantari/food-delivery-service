package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer > {

    List<MenuItem> findByRestaurantId(Integer restaurantId);
    List<MenuItem> findByRestaurant(Restaurant restaurant);
}