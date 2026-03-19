package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "items", target = "items")
    OrderDTO toDto(Order entity);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "driverId", target = "driver.id")
    @Mapping(source = "items", target = "items")
    Order toEntity(OrderDTO dto);

    List<OrderDTO> toDtoList(List<Order> entities);
}