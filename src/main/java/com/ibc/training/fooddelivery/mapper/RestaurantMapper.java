package com.ibc.training.fooddelivery.mapper;


import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.entity.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDTO toDto(Restaurant entity);

    Restaurant toEntity(RestaurantDTO dto);

    List<RestaurantDTO> toDtoList(List<Restaurant> entities);
}