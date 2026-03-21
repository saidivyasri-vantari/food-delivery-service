package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.mapper.MenuItemMapper;
import com.ibc.training.fooddelivery.repository.MenuItemRepository;
import com.ibc.training.fooddelivery.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public MenuItemDTO getMenuItemById(Integer id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        return menuItemMapper.toDto(menuItem);
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByRestaurant(Integer restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(menuItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        MenuItem entity = menuItemMapper.toEntity(dto);
        MenuItem saved = menuItemRepository.save(entity);
        return menuItemMapper.toDto(saved);
    }

    @Override
    public MenuItemDTO updateMenuItem(Integer id, MenuItemDTO dto) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItemMapper.updateEntityFromDTO(dto, existing);
        MenuItem updated = menuItemRepository.save(existing);
        return menuItemMapper.toDto(updated);
    }

    @Override
    public void deleteMenuItem(Integer id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItemRepository.delete(menuItem);
    }
}