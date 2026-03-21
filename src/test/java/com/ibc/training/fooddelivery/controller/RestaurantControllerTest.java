package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.dto.RestaurantDTO;
import com.ibc.training.fooddelivery.request.RestaurantRequest;
import com.ibc.training.fooddelivery.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private RestaurantDTO restaurantDTO;
    private RestaurantRequest restaurantRequest;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
        objectMapper = new ObjectMapper();

        restaurantRequest = new RestaurantRequest();
        restaurantRequest.setName("Burger King");
        restaurantRequest.setAddress("456 Oak Ave");
        restaurantRequest.setPhone("5559876543");

        restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(1L);
        restaurantDTO.setName("Burger King");
        restaurantDTO.setAddress("456 Oak Ave");
        restaurantDTO.setPhone("5559876543");
    }

    @Test
    public void testCreateRestaurant() throws Exception {
        when(restaurantService.createRestaurant(any(RestaurantDTO.class))).thenReturn(restaurantDTO);

        mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Burger King"));

        verify(restaurantService, times(1)).createRestaurant(any(RestaurantDTO.class));
    }

    @Test
    public void testGetRestaurantById() throws Exception {
        when(restaurantService.getRestaurantById(anyInt())).thenReturn(restaurantDTO);

        mockMvc.perform(get("/api/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("Burger King"));

        verify(restaurantService, times(1)).getRestaurantById(1);
    }

    @Test
    public void testGetAllRestaurants() throws Exception {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        restaurants.add(restaurantDTO);

        when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        mockMvc.perform(get("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].name").value("Burger King"));

        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    public void testUpdateRestaurant() throws Exception {
        when(restaurantService.updateRestaurant(anyInt(), any(RestaurantDTO.class))).thenReturn(restaurantDTO);

        mockMvc.perform(put("/api/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));

        verify(restaurantService, times(1)).updateRestaurant(anyInt(), any(RestaurantDTO.class));
    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        doNothing().when(restaurantService).deleteRestaurant(anyInt());

        mockMvc.perform(delete("/api/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        verify(restaurantService, times(1)).deleteRestaurant(1);
    }
}

