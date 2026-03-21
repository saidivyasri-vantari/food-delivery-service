package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.OrderItemDTO;
import com.ibc.training.fooddelivery.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "order.id", target = "orderId")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(source = "orderId", target = "order.id")
    OrderItem toEntity(OrderItemDTO dto);

    List<OrderItemDTO> toDtoList(List<OrderItem> orderItems);
}