package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "orders", source = "orders")
    DriverDTO toDto(Driver driver);

    @Mapping(target = "orders", ignore = true)
    Driver toEntity(DriverDTO dto);

    List<DriverDTO> toDtoList(List<Driver> drivers);
}