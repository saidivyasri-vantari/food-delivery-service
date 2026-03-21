package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.CustomerDao;
import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.CustomerMapper;
import com.ibc.training.fooddelivery.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");

        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john@example.com");
        customerDTO.setPhone("1234567890");
    }

    @Test
    public void testCreateCustomer() {
        when(customerMapper.toEntity(any(CustomerDTO.class))).thenReturn(customer);
        when(customerDao.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDto(any(Customer.class))).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(customerDao, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetCustomerById() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(any(Customer.class))).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerDao, times(1)).findById(1);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(1);
        });
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        
        when(customerDao.findAll()).thenReturn(customers);
        when(customerMapper.toDto(any(Customer.class))).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerDao, times(1)).findAll();
    }

    @Test
    public void testUpdateCustomer() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.of(customer));
        when(customerDao.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDto(any(Customer.class))).thenReturn(customerDTO);

        CustomerDTO result = customerService.updateCustomer(1, customerDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerDao, times(1)).findById(1);
        verify(customerDao, times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomerNotFound() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.updateCustomer(1, customerDTO);
        });
    }

    @Test
    public void testDeleteCustomer() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(1);

        verify(customerDao, times(1)).findById(1);
        verify(customerDao, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCustomerNotFound() {
        when(customerDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.deleteCustomer(1);
        });
    }
}

