package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.DriverDao;
import com.ibc.training.fooddelivery.dao.OrderDao;
import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.DriverMapper;
import com.ibc.training.fooddelivery.mapper.OrderMapper;
import com.ibc.training.fooddelivery.service.impl.DriverServiceImpl;
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
public class DriverServiceTest {

    @Mock
    private DriverDao driverDao;

    @Mock
    private OrderDao orderDao;

    @Mock
    private DriverMapper driverMapper;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver driver;
    private DriverDTO driverDTO;

    @BeforeEach
    public void setUp() {
        driver = new Driver();
        driver.setId(1);
        driver.setName("Alice Johnson");
        driver.setPhone("1234567890");
        driver.setVehicle("Toyota Prius");

        driverDTO = new DriverDTO();
        driverDTO.setId(1L);
        driverDTO.setName("Alice Johnson");
        driverDTO.setPhone("1234567890");
        driverDTO.setVehicle("Toyota Prius");
    }

    @Test
    public void testCreateDriver() {
        when(driverMapper.toEntity(any(DriverDTO.class))).thenReturn(driver);
        when(driverDao.save(any(Driver.class))).thenReturn(driver);
        when(driverMapper.toDto(any(Driver.class))).thenReturn(driverDTO);

        DriverDTO result = driverService.createDriver(driverDTO);

        assertNotNull(result);
        assertEquals("Alice Johnson", result.getName());
        verify(driverDao, times(1)).save(any(Driver.class));
    }

    @Test
    public void testGetDriverById() {
        when(driverDao.findById(anyInt())).thenReturn(Optional.of(driver));
        when(driverMapper.toDto(any(Driver.class))).thenReturn(driverDTO);

        DriverDTO result = driverService.getDriverById(1);

        assertNotNull(result);
        assertEquals("Alice Johnson", result.getName());
        verify(driverDao, times(1)).findById(1);
    }

    @Test
    public void testGetDriverByIdNotFound() {
        when(driverDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            driverService.getDriverById(1);
        });
    }

    @Test
    public void testGetAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);

        when(driverDao.findAll()).thenReturn(drivers);
        when(driverMapper.toDto(any(Driver.class))).thenReturn(driverDTO);

        List<DriverDTO> result = driverService.getAllDrivers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(driverDao, times(1)).findAll();
    }

    @Test
    public void testDeleteDriver() {
        when(driverDao.findById(anyInt())).thenReturn(Optional.of(driver));

        driverService.deleteDriver(1);

        verify(driverDao, times(1)).findById(1);
        verify(driverDao, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteDriverNotFound() {
        when(driverDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            driverService.deleteDriver(1);
        });
    }

    @Test
    public void testAssignDriverToOrder() {
        Order order = new Order();
        order.setId(1);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1);

        when(orderDao.findById(anyInt())).thenReturn(Optional.of(order));
        when(driverDao.findById(anyInt())).thenReturn(Optional.of(driver));
        when(orderDao.save(any(Order.class))).thenReturn(order);
        when(orderMapper.toDto(any(Order.class))).thenReturn(orderDTO);

        OrderDTO result = driverService.assignDriverToOrder(1, 1);

        assertNotNull(result);
        verify(orderDao, times(1)).save(any(Order.class));
    }
}

