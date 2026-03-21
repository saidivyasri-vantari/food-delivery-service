package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DeliveryAddressMapper.class, OrderMapper.class})
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO dto);

    List<CustomerDTO> toDtoList(List<Customer> customers);

    List<Customer> toEntityList(List<CustomerDTO> dtos);

    void updateEntityFromDTO(CustomerDTO dto, @MappingTarget Customer entity);

}