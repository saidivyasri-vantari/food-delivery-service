package com.ibc.training.fooddelivery.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.request.DriverRequest;
import com.ibc.training.fooddelivery.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DriverIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DriverRepository driverRepository;

    private ObjectMapper objectMapper;
    private DriverRequest driverRequest;

    @BeforeEach
    public void setUp() {
        driverRepository.deleteAll();
        objectMapper = new ObjectMapper();

        driverRequest = new DriverRequest();
        driverRequest.setName("Bob Driver");
        driverRequest.setPhone("9876543210");
        driverRequest.setVehicle("Honda Civic");
    }

    @Test
    public void testCreateAndRetrieveDriver() throws Exception {
        mockMvc.perform(post("/api/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driverRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.name").value("Bob Driver"));

        mockMvc.perform(get("/api/drivers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value("Bob Driver"));
    }

    @Test
    public void testUpdateDriver() throws Exception {
        Driver driver = new Driver();
        driver.setName("Alice Johnson");
        driver.setPhone("1111111111");
        driver.setVehicle("Toyota Prius");
        Driver savedDriver = driverRepository.save(driver);

        driverRequest.setName("Updated Driver");
        driverRequest.setVehicle("Tesla Model 3");

        mockMvc.perform(put("/api/drivers/" + savedDriver.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driverRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testDeleteDriver() throws Exception {
        Driver driver = new Driver();
        driver.setName("To Delete");
        driver.setPhone("2222222222");
        driver.setVehicle("Ford Focus");
        Driver savedDriver = driverRepository.save(driver);

        mockMvc.perform(delete("/api/drivers/" + savedDriver.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        mockMvc.perform(get("/api/drivers/" + savedDriver.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetDriverNotFound() throws Exception {
        mockMvc.perform(get("/api/drivers/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}

