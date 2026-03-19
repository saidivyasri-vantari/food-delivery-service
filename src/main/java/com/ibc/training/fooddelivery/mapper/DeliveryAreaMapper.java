package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.DeliveryAreaDTO;
import com.ibc.training.fooddelivery.entity.DeliveryArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryAreaMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    DeliveryAreaDTO toDto(DeliveryArea entity);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    DeliveryArea toEntity(DeliveryAreaDTO dto);

    List<DeliveryAreaDTO> toDtoList(List<DeliveryArea> entities);
}