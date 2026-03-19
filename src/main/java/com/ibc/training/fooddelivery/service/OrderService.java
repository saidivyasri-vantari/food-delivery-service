package com.ibc.training.fooddelivery.service;


import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderDTO orderDTO);

    OrderResponseDTO updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);

    OrderResponseDTO getOrderById(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    List<OrderResponseDTO> getOrdersByCustomer(Long customerId);
}