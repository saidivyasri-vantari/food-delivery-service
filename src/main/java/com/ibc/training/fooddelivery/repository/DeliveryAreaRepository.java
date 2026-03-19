package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.DeliveryArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAreaRepository extends JpaRepository<DeliveryArea, Long> {

    List<DeliveryArea> findByRestaurantId(Long restaurantId);
}