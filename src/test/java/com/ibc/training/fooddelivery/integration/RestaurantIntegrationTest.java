package com.ibc.training.fooddelivery.integration;

import com.ibc.training.fooddelivery.entity.Restaurant;
import com.ibc.training.fooddelivery.request.RestaurantRequest;
import com.ibc.training.fooddelivery.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RestaurantIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private ObjectMapper objectMapper;
    private RestaurantRequest restaurantRequest;

    @BeforeEach
    public void setUp() {
        restaurantRepository.deleteAll();
        objectMapper = new ObjectMapper();

        restaurantRequest = new RestaurantRequest();
        restaurantRequest.setName("Burger King");
        restaurantRequest.setAddress("456 Oak Ave");
        restaurantRequest.setPhone("5559876543");
    }

    @Test
    public void testCreateAndRetrieveRestaurant() throws Exception {
        mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Burger King"));

        mockMvc.perform(get("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value("Burger King"));
    }

    @Test
    public void testUpdateRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Pizza Palace");
        restaurant.setAddress("123 Main St");
        restaurant.setPhone("5551234567");
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        restaurantRequest.setName("Updated Pizza Palace");
        restaurantRequest.setAddress("789 Elm St");

        mockMvc.perform(put("/api/restaurants/" + savedRestaurant.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("Updated Pizza Palace"));
    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("To Delete");
        restaurant.setAddress("999 Delete Ave");
        restaurant.setPhone("5559999999");
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        mockMvc.perform(delete("/api/restaurants/" + savedRestaurant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        mockMvc.perform(get("/api/restaurants/" + savedRestaurant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRestaurantNotFound() throws Exception {
        mockMvc.perform(get("/api/restaurants/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}

