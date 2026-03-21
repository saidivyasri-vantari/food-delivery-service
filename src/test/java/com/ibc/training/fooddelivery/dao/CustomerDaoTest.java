package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.repository.CustomerRepository;
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
public class CustomerDaoTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerDao customerDao;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");
    }

    @Test
    public void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerDao.save(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testFindCustomerById() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerDao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerDao.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomer() {
        customerDao.deleteById(1);

        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    public void testCustomerNotFound() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Customer> result = customerDao.findById(999);

        assertFalse(result.isPresent());
    }
}

