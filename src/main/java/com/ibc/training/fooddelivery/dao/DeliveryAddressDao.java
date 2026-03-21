package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.DeliveryAddress;
import com.ibc.training.fooddelivery.repository.DeliveryAddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryAddressDao {
    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressDao(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    List<DeliveryAddress> findByAddressId(Integer addressId) {
        return deliveryAddressRepository.findById(addressId).map(List::of).orElse(List.of());
    }
}
