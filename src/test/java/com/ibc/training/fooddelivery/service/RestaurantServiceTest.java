package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.RestaurantDao;
import com.ibc.training.fooddelivery.dao.MenuItemDao;
import com.ibc.training.fooddelivery.dao.RatingDao;
import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.RestaurantMapper;
import com.ibc.training.fooddelivery.mapper.MenuItemMapper;
import com.ibc.training.fooddelivery.mapper.RatingMapper;
import com.ibc.training.fooddelivery.service.impl.RestaurantServiceImpl;
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
public class RestaurantServiceTest {

    @Mock
    private RestaurantDao restaurantDao;

    @Mock
    private MenuItemDao menuItemDao;

    @Mock
    private RatingDao ratingDao;

    @Mock
    private RestaurantMapper restaurantMapper;

    @Mock
    private MenuItemMapper menuItemMapper;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant restaurant;
    private RestaurantDTO restaurantDTO;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Pizza Palace");
        restaurant.setAddress("123 Main St");
        restaurant.setPhone("5551234567");

        restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(1L);
        restaurantDTO.setName("Pizza Palace");
        restaurantDTO.setAddress("123 Main St");
        restaurantDTO.setPhone("5551234567");
    }

    @Test
    public void testCreateRestaurant() {
        when(restaurantMapper.toEntity(any(RestaurantDTO.class))).thenReturn(restaurant);
        when(restaurantDao.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantMapper.toDto(any(Restaurant.class))).thenReturn(restaurantDTO);

        RestaurantDTO result = restaurantService.createRestaurant(restaurantDTO);

        assertNotNull(result);
        assertEquals("Pizza Palace", result.getName());
        verify(restaurantDao, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testGetRestaurantById() {
        when(restaurantDao.findById(anyInt())).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.toDto(any(Restaurant.class))).thenReturn(restaurantDTO);

        RestaurantDTO result = restaurantService.getRestaurantById(1);

        assertNotNull(result);
        assertEquals("Pizza Palace", result.getName());
        verify(restaurantDao, times(1)).findById(1);
    }

    @Test
    public void testGetRestaurantByIdNotFound() {
        when(restaurantDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            restaurantService.getRestaurantById(1);
        });
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);

        when(restaurantDao.findAll()).thenReturn(restaurants);
        when(restaurantMapper.toDto(any(Restaurant.class))).thenReturn(restaurantDTO);

        List<RestaurantDTO> result = restaurantService.getAllRestaurants();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restaurantDao, times(1)).findAll();
    }

    @Test
    public void testUpdateRestaurant() {
        when(restaurantDao.findById(anyInt())).thenReturn(Optional.of(restaurant));
        when(restaurantDao.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantMapper.toDto(any(Restaurant.class))).thenReturn(restaurantDTO);

        RestaurantDTO result = restaurantService.updateRestaurant(1, restaurantDTO);

        assertNotNull(result);
        verify(restaurantDao, times(1)).findById(1);
        verify(restaurantDao, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testDeleteRestaurant() {
        when(restaurantDao.findById(anyInt())).thenReturn(Optional.of(restaurant));

        restaurantService.deleteRestaurant(1);

        verify(restaurantDao, times(1)).findById(1);
        verify(restaurantDao, times(1)).deleteById(1);
    }

    @Test
    public void testGetMenuByRestaurant() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem item = new MenuItem();
        item.setId(1);
        menuItems.add(item);

        MenuItemDTO itemDTO = new MenuItemDTO();
        itemDTO.setId(1L);

        when(menuItemDao.findByRestaurantId(anyInt())).thenReturn(menuItems);
        when(menuItemMapper.toDto(any(MenuItem.class))).thenReturn(itemDTO);

        List<MenuItemDTO> result = restaurantService.getMenuByRestaurant(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(menuItemDao, times(1)).findByRestaurantId(1);
    }
}

