package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.CustomerDTO;
import com.ibc.training.fooddelivery.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer toEntity(CustomerDTO dto);

    List<CustomerDTO> toDtoList(List<Customer> entities);
}