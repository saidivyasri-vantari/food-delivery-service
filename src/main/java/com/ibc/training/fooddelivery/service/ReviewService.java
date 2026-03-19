package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.ReviewDao;
import com.ibc.training.fooddelivery.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewDao dao;
    private final ReviewMapper mapper;

    public ReviewService(ReviewDao dao, ReviewMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<ReviewDTO> getByRestaurant(Long restaurantId) {
        return mapper.toDtoList(dao.findByRestaurantId(restaurantId));
    }

    public ReviewDTO create(ReviewDTO dto) {
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }
}