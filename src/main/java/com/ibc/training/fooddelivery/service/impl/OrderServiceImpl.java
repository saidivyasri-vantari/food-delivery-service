package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dao.CustomerDao;
import com.ibc.training.fooddelivery.dao.OrderDao;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.OrderMapper;
import com.ibc.training.fooddelivery.service.OrderService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderDao orderDao, CustomerDao customerDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        Order order = orderMapper.toEntity(orderDTO);

        // ✅ Set Customer
        Customer customer = customerDao.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        order.setCustomer(customer);

        Order savedOrder = orderDao.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {

        Order existingOrder = orderDao.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderMapper.updateEntityFromDTO(orderDTO, existingOrder);

        // ✅ Update customer if provided
        if (orderDTO.getCustomerId() != null) {
            Customer customer = customerDao.findById(orderDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
            existingOrder.setCustomer(customer);
        }

        Order updatedOrder = orderDao.save(existingOrder);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Integer orderId) {

        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderDao.deleteById(order.getId());
    }

    @Override
    public OrderDTO getOrderById(Integer orderId) {

        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {

        return orderMapper.toDtoList(orderDao.findAll());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomer(Integer customerId) {

        List<Order> orders = orderDao.findByCustomerId(customerId);
        return orderMapper.toDtoList(orders);
    }
}