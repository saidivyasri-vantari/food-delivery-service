package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.RatingDTO;
import com.ibc.training.fooddelivery.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    RatingDTO toDto(Rating rating);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Rating toEntity(RatingDTO dto);

    List<RatingDTO> toDtoList(List<Rating> ratings);

    List<Rating> toEntityList(List<RatingDTO> dtos);
}