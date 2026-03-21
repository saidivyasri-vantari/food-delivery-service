package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderDaoTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderDao orderDao;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setId(1);
        //order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
    }

    @Test
    public void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderDao.save(order);

        assertNotNull(result);
        assertEquals("PENDING", result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testFindOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        Optional<Order> result = orderDao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("PENDING", result.get().getStatus());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderDao.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindOrdersByCustomerId() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findByCustomerId(anyInt())).thenReturn(orders);

        List<Order> result = orderDao.findByCustomerId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByCustomerId(1);
    }

    @Test
    public void testDeleteOrder() {
        orderDao.deleteById(1);

        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    public void testOrderNotFound() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Order> result = orderDao.findById(999);

        assertFalse(result.isPresent());
    }
}

