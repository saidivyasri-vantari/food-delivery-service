package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDao {

    private final CustomerRepository repository;

    public CustomerDao(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}