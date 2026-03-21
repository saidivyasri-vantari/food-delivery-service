package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dao.CustomerDao;
import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.CustomerMapper;
import com.ibc.training.fooddelivery.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerDao customerDao, CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDao.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer entity = customerMapper.toEntity(dto);
        Customer saved = customerDao.save(entity);
        return customerMapper.toDto(saved);
    }

    @Override
    public CustomerDTO updateCustomer(Integer id, CustomerDTO dto) {
        Customer existing = customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerMapper.updateEntityFromDTO(dto, existing);
        Customer updated = customerDao.save(existing);
        return customerMapper.toDto(updated);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customerDao.deleteById(customer.getId());

    }
}