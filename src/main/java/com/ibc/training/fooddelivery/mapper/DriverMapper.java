package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.entity.Driver;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDTO toDto(Driver entity);

    Driver toEntity(DriverDTO dto);

    List<DriverDTO> toDtoList(List<Driver> entities);
}