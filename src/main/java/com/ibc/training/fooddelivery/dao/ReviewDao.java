package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Review;
import com.ibc.training.fooddelivery.repository.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewDao {

    private final ReviewRepository repository;

    public ReviewDao(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review save(Review review) {
        return repository.save(review);
    }

    public List<Review> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }
}