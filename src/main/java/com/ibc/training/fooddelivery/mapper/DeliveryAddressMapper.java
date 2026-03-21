package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.DeliveryAddressDTO;
import com.ibc.training.fooddelivery.entity.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper {

    @Mapping(source = "customer.id", target = "customerId")
    DeliveryAddressDTO toDto(DeliveryAddress address);

    @Mapping(source = "customerId", target = "customer.id")
    DeliveryAddress toEntity(DeliveryAddressDTO dto);

    List<DeliveryAddressDTO> toDtoList(List<DeliveryAddress> addresses);
}