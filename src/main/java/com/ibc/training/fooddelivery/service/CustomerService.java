package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.CustomerDao;
import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao dao;
    private final CustomerMapper mapper;

    public CustomerService(CustomerDao dao, CustomerMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    public CustomerDTO getCustomer(Long id) {
        return mapper.toDto(
                dao.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"))
        );
    }

    public List<CustomerDTO> getAllCustomers() {
        return mapper.toDtoList(dao.findAll());
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        Customer existing = dao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id: " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());

        return mapper.toDto(dao.save(existing));
    }

    public void deleteCustomer(Long id) {

        Customer existing = dao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found with id: " + id));

        dao.deleteById(existing.getId());
    }
}