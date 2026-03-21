package com.ibc.training.fooddelivery.integration;

import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.request.OrderRequest;
import com.ibc.training.fooddelivery.repository.OrderRepository;
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

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper;
    private OrderRequest orderRequest;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail("test@example.com");
        customer.setPhone("1234567890");
        customer = customerRepository.save(customer);

        orderRequest = new OrderRequest();
        orderRequest.setCustomerId(customer.getId());
        orderRequest.setRestaurantId(1);
        orderRequest.setStatus("PENDING");
        orderRequest.setOrderDate(LocalDateTime.now());
    }

    @Test
    public void testCreateAndRetrieveOrder() throws Exception {
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.status").value("PENDING"));

        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order();
        //order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCustomer(customer);
        Order savedOrder = orderRepository.save(order);

        orderRequest.setStatus("CONFIRMED");

        mockMvc.perform(put("/api/orders/" + savedOrder.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Order order = new Order();
        //order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCustomer(customer);
        Order savedOrder = orderRepository.save(order);

        mockMvc.perform(delete("/api/orders/" + savedOrder.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        mockMvc.perform(get("/api/orders/" + savedOrder.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOrdersByCustomer() throws Exception {
        Order order = new Order();
        //order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCustomer(customer);
        orderRepository.save(order);

        mockMvc.perform(get("/api/orders/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)));
    }
}

