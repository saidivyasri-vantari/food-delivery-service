package com.ibc.training.fooddelivery.integration;

import com.ibc.training.fooddelivery.entity.MenuItem;
import com.ibc.training.fooddelivery.request.MenuItemRequest;
import com.ibc.training.fooddelivery.repository.MenuItemRepository;
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
public class MenuItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private ObjectMapper objectMapper;
    private MenuItemRequest menuItemRequest;

    @BeforeEach
    public void setUp() {
        menuItemRepository.deleteAll();
        objectMapper = new ObjectMapper();

        menuItemRequest = new MenuItemRequest();
        menuItemRequest.setName("Spaghetti");
        menuItemRequest.setDescription("Delicious spaghetti");
        menuItemRequest.setPrice(10.50);
        menuItemRequest.setRestaurantId(1L);
    }

    @Test
    public void testCreateAndRetrieveMenuItem() throws Exception {
        mockMvc.perform(post("/api/menu-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Spaghetti"));

        mockMvc.perform(get("/api/menu-items/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    public void testUpdateMenuItem() throws Exception {
        MenuItem menuItem = new MenuItem();
        menuItem.setName("Pizza");
        menuItem.setDescription("Classic pizza");
        menuItem.setPrice(12.99);
        MenuItem savedItem = menuItemRepository.save(menuItem);

        menuItemRequest.setName("Updated Pizza");
        menuItemRequest.setPrice(14.99);

        mockMvc.perform(put("/api/menu-items/" + savedItem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testDeleteMenuItem() throws Exception {
        MenuItem menuItem = new MenuItem();
        menuItem.setName("To Delete");
        menuItem.setDescription("This will be deleted");
        menuItem.setPrice(5.99);
        MenuItem savedItem = menuItemRepository.save(menuItem);

        mockMvc.perform(delete("/api/menu-items/" + savedItem.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        mockMvc.perform(get("/api/menu-items/" + savedItem.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

