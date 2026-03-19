package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.OrderItemDTO;
import com.ibc.training.fooddelivery.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "menuItem.id", target = "menuItemId")
    OrderItemDTO toDto(OrderItem entity);

    @Mapping(source = "menuItemId", target = "menuItem.id")
    OrderItem toEntity(OrderItemDTO dto);

    List<OrderItemDTO> toDtoList(List<OrderItem> entities);
}