package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.ReviewDTO;
import com.ibc.training.fooddelivery.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    ReviewDTO toDto(Review entity);

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "restaurantId", target = "restaurant.id")
    Review toEntity(ReviewDTO dto);

    List<ReviewDTO> toDtoList(List<Review> entities);
}