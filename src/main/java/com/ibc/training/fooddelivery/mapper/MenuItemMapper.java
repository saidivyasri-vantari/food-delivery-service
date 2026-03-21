package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuItemDTO toDto(MenuItem menuItem);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    MenuItem toEntity(MenuItemDTO dto);

    List<MenuItemDTO> toDtoList(List<MenuItem> menuItems);
    // Update existing Entity from DTO
    void updateEntityFromDTO(MenuItemDTO dto, @MappingTarget MenuItem entity);
}