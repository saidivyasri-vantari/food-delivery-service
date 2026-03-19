package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByCustomerId(Long customerId);

    Optional<Favorite> findByCustomerIdAndRestaurantId(Long customerId, Long restaurantId);
}