package com.ibc.training.fooddelivery.service;


import com.ibc.training.fooddelivery.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO);

    void deleteOrder(Integer orderId);

    OrderDTO getOrderById(Integer orderId);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByCustomer(Integer customerId);
}