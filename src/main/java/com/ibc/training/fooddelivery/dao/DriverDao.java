package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.repository.DriverRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DriverDao {

    private final DriverRepository repository;

    public DriverDao(DriverRepository repository) {
        this.repository = repository;
    }

    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    public Optional<Driver> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer  id) {
        repository.deleteById(id);
    }
}