package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {
    List<DeliveryAddress> findByCustomerId(Integer customerId);
}
