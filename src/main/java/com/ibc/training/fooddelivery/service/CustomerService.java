package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer id);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO);
    void deleteCustomer(Integer id);
}