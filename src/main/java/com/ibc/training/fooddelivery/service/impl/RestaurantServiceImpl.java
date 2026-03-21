package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dao.MenuItemDao;
import com.ibc.training.fooddelivery.dao.RatingDao;
import com.ibc.training.fooddelivery.dao.RestaurantDao;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.dto.RatingDTO;
import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.MenuItemMapper;
import com.ibc.training.fooddelivery.mapper.RatingMapper;
import com.ibc.training.fooddelivery.mapper.RestaurantMapper;
import com.ibc.training.fooddelivery.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDao restaurantDao;
    private final MenuItemDao menuItemDao;
    private final RatingDao ratingDao;

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;
    private final RatingMapper ratingMapper;

    public RestaurantServiceImpl(RestaurantDao restaurantDao, MenuItemDao menuItemDao,
                                 RatingDao ratingDao,
                                 RestaurantMapper restaurantMapper, MenuItemMapper
                                         menuItemMapper, RatingMapper ratingMapper) {
        this.restaurantDao = restaurantDao;
        this.menuItemDao = menuItemDao;
        this.ratingDao = ratingDao;
        this.restaurantMapper = restaurantMapper;
        this.menuItemMapper = menuItemMapper;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        return restaurantMapper.toDto(
                restaurantDao.save(restaurantMapper.toEntity(dto)));
    }

    @Override
    public RestaurantDTO updateRestaurant(Integer id, RestaurantDTO dto) {

        Restaurant restaurant = restaurantDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        restaurantMapper.updateEntityFromDTO(dto, restaurant);
        return restaurantMapper.toDto(restaurantDao.save(restaurant));
    }

    @Override
    public void deleteRestaurant(Integer id) {

        Restaurant restaurant = restaurantDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        restaurantDao.deleteById(restaurant.getId());
    }

    @Override
    public RestaurantDTO getRestaurantById(Integer id) {

        return restaurantMapper.toDto(
                restaurantDao.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found")));
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantMapper.toDtoList(restaurantDao.findAll());
    }

    @Override
    public List<MenuItemDTO> getMenuByRestaurant(Integer restaurantId) {

        return menuItemMapper.toDtoList(
                menuItemDao.findByRestaurantId(restaurantId));
    }

    @Override
    public List<RatingDTO> getReviewsByRestaurant(Integer restaurantId) {

        return ratingMapper.toDtoList(
                ratingDao.findByRestaurantId(restaurantId));
    }
}