package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "menuItems", source = "menuItems")
    @Mapping(target = "ratings", source = "ratings")
    RestaurantDTO toDto(Restaurant restaurant);

    @Mapping(target = "menuItems", source = "menuItems")
    @Mapping(target = "ratings", source = "ratings")
    Restaurant toEntity(RestaurantDTO dto);

    List<RestaurantDTO> toDtoList(List<Restaurant> restaurants);

    void updateEntityFromDTO(RestaurantDTO dto, @MappingTarget Restaurant entity);

}