package com.ibc.training.fooddelivery.repository;

import com.ibc.training.fooddelivery.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}