package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.repository.RestaurantRepository;
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
public class RestaurantDaoTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantDao restaurantDao;

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Pizza Palace");
        restaurant.setAddress("123 Main St");
        restaurant.setPhone("5551234567");
    }

    @Test
    public void testSaveRestaurant() {
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant result = restaurantDao.save(restaurant);

        assertNotNull(result);
        assertEquals("Pizza Palace", result.getName());
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testFindRestaurantById() {
        when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> result = restaurantDao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Pizza Palace", result.get().getName());
        verify(restaurantRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant);

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<Restaurant> result = restaurantDao.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteRestaurant() {
        restaurantDao.deleteById(1);

        verify(restaurantRepository, times(1)).deleteById(1);
    }

    @Test
    public void testRestaurantNotFound() {
        when(restaurantRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Restaurant> result = restaurantDao.findById(999);

        assertFalse(result.isPresent());
    }
}

