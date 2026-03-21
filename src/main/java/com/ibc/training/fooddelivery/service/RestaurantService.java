package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.dto.RatingDTO;
import com.ibc.training.fooddelivery.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    RestaurantDTO updateRestaurant(Integer id, RestaurantDTO dto);

    void deleteRestaurant(Integer id);

    RestaurantDTO getRestaurantById(Integer id);

    List<RestaurantDTO> getAllRestaurants();

    // ✅ New APIs
    List<MenuItemDTO> getMenuByRestaurant(Integer restaurantId);

    List<RatingDTO> getReviewsByRestaurant(Integer restaurantId);
}