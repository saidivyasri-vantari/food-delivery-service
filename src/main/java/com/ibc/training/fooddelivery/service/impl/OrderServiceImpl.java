package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.dto.OrderItemDTO;
import com.ibc.training.fooddelivery.dto.OrderResponseDTO;
import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.entity.OrderItem;
import com.ibc.training.fooddelivery.repository.CustomerRepository;
import com.ibc.training.fooddelivery.repository.OrderItemRepository;
import com.ibc.training.fooddelivery.repository.OrderRepository;
import com.ibc.training.fooddelivery.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());

        // Calculate total amount
        BigDecimal totalAmount = orderDTO.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Save order items
        List<OrderItem> items = orderDTO.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setMenuItemId(itemDTO.getMenuItemId());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());
            item.setOrder(savedOrder);
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());

        return mapToResponseDTO(savedOrder, items);
    }

    @Override
    public OrderResponseDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setStatus(orderDTO.getStatus());

        // Update total amount
        BigDecimal totalAmount = orderDTO.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        // Remove old items and save new items
        orderItemRepository.deleteByOrderId(orderId);
        List<OrderItem> items = orderDTO.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setMenuItemId(itemDTO.getMenuItemId());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());
            item.setOrder(order);
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());

        return mapToResponseDTO(order, items);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderItemRepository.deleteByOrderId(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
        return mapToResponseDTO(order, items);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> mapToResponseDTO(order, orderItemRepository.findByOrderId(order.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(order -> mapToResponseDTO(order, orderItemRepository.findByOrderId(order.getId())))
                .collect(Collectors.toList());
    }

    // Utility method to map Order + Items -> OrderResponseDTO
    private OrderResponseDTO mapToResponseDTO(Order order, List<OrderItem> items) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setOrderTime(order.getOrderTime());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setDeliveryAddress(order.getDeliveryAddress());

        List<OrderItemDTO> itemDTOs = items.stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setMenuItemId(item.getMenuItemId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }
}