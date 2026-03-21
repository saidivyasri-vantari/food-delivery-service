package com.ibc.training.fooddelivery.controller;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.request.DriverRequest;
import com.ibc.training.fooddelivery.service.DriverService;
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
public class DriverControllerTest {

    @Mock
    private DriverService driverService;

    @InjectMocks
    private DriverController driverController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private DriverDTO driverDTO;
    private DriverRequest driverRequest;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
        objectMapper = new ObjectMapper();

        driverRequest = new DriverRequest();
        driverRequest.setName("Bob Driver");
        driverRequest.setPhone("9876543210");
        driverRequest.setVehicle("Honda Civic");

        driverDTO = new DriverDTO();
        driverDTO.setId(1L);
        driverDTO.setName("Bob Driver");
        driverDTO.setPhone("9876543210");
        driverDTO.setVehicle("Honda Civic");
    }

    @Test
    public void testCreateDriver() throws Exception {
        when(driverService.createDriver(any(DriverDTO.class))).thenReturn(driverDTO);

        mockMvc.perform(post("/api/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driverRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Bob Driver"));

        verify(driverService, times(1)).createDriver(any(DriverDTO.class));
    }

    @Test
    public void testGetAllDrivers() throws Exception {
        List<DriverDTO> drivers = new ArrayList<>();
        drivers.add(driverDTO);

        when(driverService.getAllDrivers()).thenReturn(drivers);

        mockMvc.perform(get("/api/drivers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].name").value("Bob Driver"));

        verify(driverService, times(1)).getAllDrivers();
    }

    @Test
    public void testGetDriverById() throws Exception {
        when(driverService.getDriverById(anyInt())).thenReturn(driverDTO);

        mockMvc.perform(get("/api/drivers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("Bob Driver"));

        verify(driverService, times(1)).getDriverById(1);
    }

    @Test
    public void testDeleteDriver() throws Exception {
        doNothing().when(driverService).deleteDriver(anyInt());

        mockMvc.perform(delete("/api/drivers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        verify(driverService, times(1)).deleteDriver(1);
    }
}

