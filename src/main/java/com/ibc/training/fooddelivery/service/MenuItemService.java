package com.ibc.training.fooddelivery.service;


import com.ibc.training.fooddelivery.dao.MenuItemDao;
import com.ibc.training.fooddelivery.dao.RestaurantDao;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemDao menuItemDao;
    private final RestaurantDao restaurantDao;
    private final MenuItemMapper mapper;

    // ✅ GET all menu items by restaurant
    public List<MenuItemDTO> getMenuByRestaurant(Long restaurantId) {

        // Validate restaurant exists
        Restaurant restaurant = restaurantDao.findById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        return mapper.toDtoList(menuItemDao.findByRestaurant(restaurant));
    }

    // ✅ CREATE menu item
    public MenuItemDTO createMenuItem(Long restaurantId, MenuItemDTO dto) {

        Restaurant restaurant = restaurantDao.findById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        MenuItem entity = mapper.toEntity(dto);
        entity.setRestaurant(restaurant); // 🔥 important relationship

        return mapper.toDto(menuItemDao.save(entity));
    }

    // ✅ GET menu item by ID
    public MenuItemDTO getMenuItem(Long menuItemId) {

        return mapper.toDto(
                menuItemDao.findById(menuItemId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Menu item not found with id: " + menuItemId))
        );
    }

    // ✅ UPDATE menu item
    public MenuItemDTO updateMenuItem(Long menuItemId, MenuItemDTO dto) {

        MenuItem existing = menuItemDao.findById(menuItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found with id: " + menuItemId));

        // Update fields
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.getAvailable());

        return mapper.toDto(menuItemDao.save(existing));
    }

    // ✅ DELETE menu item
    public void deleteMenuItem(Long menuItemId) {

        MenuItem existing = menuItemDao.findById(menuItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found with id: " + menuItemId));

        menuItemDao.deleteById(existing.getId());
    }
}