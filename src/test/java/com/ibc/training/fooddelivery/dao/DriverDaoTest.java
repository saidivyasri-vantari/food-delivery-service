package com.ibc.training.fooddelivery.dao;

import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.repository.DriverRepository;
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
public class DriverDaoTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverDao driverDao;

    private Driver driver;

    @BeforeEach
    public void setUp() {
        driver = new Driver();
        driver.setId(1);
        driver.setName("Alice Johnson");
        driver.setPhone("1234567890");
        driver.setVehicle("Toyota Prius");
    }

    @Test
    public void testSaveDriver() {
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        Driver result = driverDao.save(driver);

        assertNotNull(result);
        assertEquals("Alice Johnson", result.getName());
        verify(driverRepository, times(1)).save(any(Driver.class));
    }

    @Test
    public void testFindDriverById() {
        when(driverRepository.findById(anyInt())).thenReturn(Optional.of(driver));

        Optional<Driver> result = driverDao.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Alice Johnson", result.get().getName());
        verify(driverRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);

        when(driverRepository.findAll()).thenReturn(drivers);

        List<Driver> result = driverDao.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteDriver() {
        driverDao.deleteById(1);

        verify(driverRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDriverNotFound() {
        when(driverRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Driver> result = driverDao.findById(999);

        assertFalse(result.isPresent());
    }
}

