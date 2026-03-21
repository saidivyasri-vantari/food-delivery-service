package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.MenuItemDao;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.mapper.MenuItemMapper;
import com.ibc.training.fooddelivery.repository.MenuItemRepository;
import com.ibc.training.fooddelivery.service.impl.MenuItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    private MenuItem menuItem;
    private MenuItemDTO menuItemDTO;

    @BeforeEach
    public void setUp() {
        menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setName("Pasta Carbonara");
        menuItem.setDescription("Classic Italian pasta");
        menuItem.setPrice(12.99);

        menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(1L);
        menuItemDTO.setName("Pasta Carbonara");
        menuItemDTO.setDescription("Classic Italian pasta");
        menuItemDTO.setPrice(12.99);
    }

    @Test
    public void testCreateMenuItem() {
        when(menuItemMapper.toEntity(any(MenuItemDTO.class))).thenReturn(menuItem);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        when(menuItemMapper.toDto(any(MenuItem.class))).thenReturn(menuItemDTO);

        MenuItemDTO result = menuItemService.createMenuItem(menuItemDTO);

        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getName());
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }

    @Test
    public void testGetMenuItemById() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.of(menuItem));
        when(menuItemMapper.toDto(any(MenuItem.class))).thenReturn(menuItemDTO);

        MenuItemDTO result = menuItemService.getMenuItemById(1);

        assertNotNull(result);
        assertEquals("Pasta Carbonara", result.getName());
        verify(menuItemRepository, times(1)).findById(1);
    }

    @Test
    public void testGetMenuItemsByRestaurant() {
        List<MenuItem> items = new ArrayList<>();
        items.add(menuItem);

        when(menuItemRepository.findByRestaurantId(anyInt())).thenReturn(items);
        when(menuItemMapper.toDto(any(MenuItem.class))).thenReturn(menuItemDTO);

        List<MenuItemDTO> result = menuItemService.getMenuItemsByRestaurant(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(menuItemRepository, times(1)).findByRestaurantId(1);
    }

    @Test
    public void testUpdateMenuItem() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.of(menuItem));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        when(menuItemMapper.toDto(any(MenuItem.class))).thenReturn(menuItemDTO);

        MenuItemDTO result = menuItemService.updateMenuItem(1, menuItemDTO);

        assertNotNull(result);
        verify(menuItemRepository, times(1)).findById(1);
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }

    @Test
    public void testDeleteMenuItem() {
        when(menuItemRepository.findById(anyInt())).thenReturn(Optional.of(menuItem));

        menuItemService.deleteMenuItem(1);

        verify(menuItemRepository, times(1)).findById(1);
        verify(menuItemRepository, times(1)).delete(any(MenuItem.class));
    }
}

