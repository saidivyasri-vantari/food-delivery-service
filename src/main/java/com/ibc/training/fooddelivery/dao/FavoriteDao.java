package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Favorite;
import com.ibc.training.fooddelivery.repository.FavoriteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FavoriteDao {

    private final FavoriteRepository repository;

    public FavoriteDao(FavoriteRepository repository) {
        this.repository = repository;
    }

    public Favorite save(Favorite favorite) {
        return repository.save(favorite);
    }

    public List<Favorite> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Optional<Favorite> findByCustomerIdAndRestaurantId(Long customerId, Long restaurantId) {
        return repository.findByCustomerIdAndRestaurantId(customerId, restaurantId);
    }
}