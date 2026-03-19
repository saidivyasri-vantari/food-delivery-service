package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.RestaurantDao;
import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantDao dao;
    private final RestaurantMapper mapper;

    public RestaurantService(RestaurantDao dao, RestaurantMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return mapper.toDtoList(dao.findAll());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return mapper.toDto(restaurant);
    }

    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = mapper.toEntity(dto);
        return mapper.toDto(dao.save(restaurant));
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO dto) {
        Restaurant existing = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setCuisineType(dto.getCuisineType());

        return mapper.toDto(dao.save(existing));
    }

    public void deleteRestaurant(Long id) {
        if (!dao.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found");
        }
        dao.deleteById(id);
    }
}