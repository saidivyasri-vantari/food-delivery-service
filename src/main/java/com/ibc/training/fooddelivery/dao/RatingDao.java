package com.ibc.training.fooddelivery.dao;


import com.ibc.training.fooddelivery.entity.Rating;
import com.ibc.training.fooddelivery.repository.RatingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingDao {
    private final RatingRepository repository;
    public RatingDao(RatingRepository repository) {
        this.repository = repository;
    }

    public List<Rating> findByRestaurantId(Integer restaurantId){
        return repository.findByRestaurantId(restaurantId);
    }
    public List<Rating> findByOrderId(Integer orderId){
        return repository.findByOrderId(orderId);
    }

}
