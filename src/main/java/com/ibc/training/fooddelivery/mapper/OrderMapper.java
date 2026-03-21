package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    // Entity -> DTO
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    @Mapping(source = "driver.id", target = "driverId")
    OrderDTO toDto(Order order);

    // DTO -> Entity
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    @Mapping(source = "driverId", target = "driver.id")
    Order toEntity(OrderDTO dto);

    void updateEntityFromDTO(OrderDTO dto, @MappingTarget Order entity);

    List<OrderDTO> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDTO> dtos);
}