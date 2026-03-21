package com.ibc.training.fooddelivery;


import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.request.CustomerRequest;
import com.ibc.training.fooddelivery.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerRequest customerRequest;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();

        customerRequest = new CustomerRequest();
        customerRequest.setName("Jane Doe");
        customerRequest.setEmail("jane@example.com");
        customerRequest.setPhone("0987654321");
    }

    @Test
    public void testCreateAndRetrieveCustomer() throws Exception {
        // Create a customer
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Jane Doe"));

        // Retrieve the customer
        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value("Jane Doe"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Create a customer first
        Customer customer = new Customer();
        customer.setName("John Smith");
        customer.setEmail("john.smith@example.com");
        customer.setPhone("1111111111");
        Customer savedCustomer = customerRepository.save(customer);

        // Update the customer
        customerRequest.setName("Updated Name");
        customerRequest.setEmail("updated@example.com");

        mockMvc.perform(put("/api/customers/" + savedCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("Updated Name"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // Create a customer first
        Customer customer = new Customer();
        customer.setName("To Delete");
        customer.setEmail("delete@example.com");
        customer.setPhone("2222222222");
        Customer savedCustomer = customerRepository.save(customer);

        // Delete the customer
        mockMvc.perform(delete("/api/customers/" + savedCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        // Verify the customer is deleted
        mockMvc.perform(get("/api/customers/" + savedCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomerNotFound() throws Exception {
        mockMvc.perform(get("/api/customers/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}

