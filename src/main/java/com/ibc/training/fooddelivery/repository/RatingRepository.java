package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByRestaurantId(Integer restaurantId);
    List<Rating> findByOrderId(Integer orderId);

}