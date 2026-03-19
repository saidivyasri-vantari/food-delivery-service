package com.ibc.training.fooddelivery.mapper;


import com.ibc.training.fooddelivery.dto.FavoriteDTO;
import com.ibc.training.fooddelivery.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    FavoriteDTO toDto(Favorite entity);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Favorite toEntity(FavoriteDTO dto);

    List<FavoriteDTO> toDtoList(List<Favorite> entities);
}