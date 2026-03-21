package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.request.OrderRequest;
import com.ibc.training.fooddelivery.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private OrderDTO orderDTO;
    private OrderRequest orderRequest;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        orderRequest = new OrderRequest();
        orderRequest.setCustomerId(1);
        orderRequest.setRestaurantId(1);
        orderRequest.setStatus("PENDING");

        orderDTO = new OrderDTO();
        orderDTO.setId(1);
        orderDTO.setCustomerId(1);
        orderDTO.setRestaurantId(1);
        orderDTO.setStatus("PENDING");
        orderDTO.setOrderDate(LocalDateTime.now());
    }

    @Test
    public void testCreateOrder() throws Exception {
        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.status").value("PENDING"));

        verify(orderService, times(1)).createOrder(any(OrderDTO.class));
    }

    @Test
    public void testGetOrderById() throws Exception {
        when(orderService.getOrderById(anyInt())).thenReturn(orderDTO);

        mockMvc.perform(get("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.status").value("PENDING"));

        verify(orderService, times(1)).getOrderById(1);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<OrderDTO> orders = new ArrayList<>();
        orders.add(orderDTO);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].status").value("PENDING"));

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        when(orderService.updateOrder(anyInt(), any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));

        verify(orderService, times(1)).updateOrder(anyInt(), any(OrderDTO.class));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(anyInt());

        mockMvc.perform(delete("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        verify(orderService, times(1)).deleteOrder(1);
    }

    @Test
    public void testGetOrdersByCustomer() throws Exception {
        List<OrderDTO> orders = new ArrayList<>();
        orders.add(orderDTO);

        when(orderService.getOrdersByCustomer(anyInt())).thenReturn(orders);

        mockMvc.perform(get("/api/orders/customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].status").value("PENDING"));

        verify(orderService, times(1)).getOrdersByCustomer(1);
    }
}

