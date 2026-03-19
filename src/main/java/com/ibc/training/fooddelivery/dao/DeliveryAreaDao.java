package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.DeliveryArea;
import com.ibc.training.fooddelivery.repository.DeliveryAreaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryAreaDao {

    private final DeliveryAreaRepository repository;

    public DeliveryAreaDao(DeliveryAreaRepository repository) {
        this.repository = repository;
    }

    public List<DeliveryArea> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }
}