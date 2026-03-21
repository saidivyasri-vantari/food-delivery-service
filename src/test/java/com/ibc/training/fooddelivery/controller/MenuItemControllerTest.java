package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.dto.MenuItemDTO;
import com.ibc.training.fooddelivery.request.MenuItemRequest;
import com.ibc.training.fooddelivery.service.MenuItemService;
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
public class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private MenuItemDTO menuItemDTO;
    private MenuItemRequest menuItemRequest;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemController).build();
        objectMapper = new ObjectMapper();

        menuItemRequest = new MenuItemRequest();
        menuItemRequest.setName("Spaghetti");
        menuItemRequest.setDescription("Delicious spaghetti");
        menuItemRequest.setPrice(10.50);
        menuItemRequest.setRestaurantId(1L);

        menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(1L);
        menuItemDTO.setName("Spaghetti");
        menuItemDTO.setDescription("Delicious spaghetti");
        menuItemDTO.setPrice(10.50);
        menuItemDTO.setRestaurantId(1L);
    }

    @Test
    public void testCreateMenuItem() throws Exception {
        when(menuItemService.createMenuItem(any(MenuItemDTO.class))).thenReturn(menuItemDTO);

        mockMvc.perform(post("/api/menu-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Spaghetti"));

        verify(menuItemService, times(1)).createMenuItem(any(MenuItemDTO.class));
    }

    @Test
    public void testGetMenuItemById() throws Exception {
        when(menuItemService.getMenuItemById(anyInt())).thenReturn(menuItemDTO);

        mockMvc.perform(get("/api/menu-items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("Spaghetti"));

        verify(menuItemService, times(1)).getMenuItemById(1);
    }

    @Test
    public void testGetMenuItemsByRestaurant() throws Exception {
        List<MenuItemDTO> items = new ArrayList<>();
        items.add(menuItemDTO);

        when(menuItemService.getMenuItemsByRestaurant(anyInt())).thenReturn(items);

        mockMvc.perform(get("/api/menu-items/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].name").value("Spaghetti"));

        verify(menuItemService, times(1)).getMenuItemsByRestaurant(1);
    }

    @Test
    public void testUpdateMenuItem() throws Exception {
        when(menuItemService.updateMenuItem(anyInt(), any(MenuItemDTO.class))).thenReturn(menuItemDTO);

        mockMvc.perform(put("/api/menu-items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));

        verify(menuItemService, times(1)).updateMenuItem(anyInt(), any(MenuItemDTO.class));
    }

    @Test
    public void testDeleteMenuItem() throws Exception {
        doNothing().when(menuItemService).deleteMenuItem(anyInt());

        mockMvc.perform(delete("/api/menu-items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        verify(menuItemService, times(1)).deleteMenuItem(1);
    }
}

