package com.ibc.training.fooddelivery.service;



import com.ibc.training.fooddelivery.dto.MenuItemDTO;

import java.util.List;

public interface MenuItemService {

    MenuItemDTO getMenuItemById(Integer id);

    List<MenuItemDTO> getMenuItemsByRestaurant(Integer restaurantId);

    MenuItemDTO createMenuItem(MenuItemDTO dto);

    MenuItemDTO updateMenuItem(Integer id, MenuItemDTO dto);

    void deleteMenuItem(Integer id);
}