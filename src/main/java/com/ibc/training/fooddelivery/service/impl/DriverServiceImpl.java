package com.ibc.training.fooddelivery.service.impl;

import com.ibc.training.fooddelivery.dao.DriverDao;
import com.ibc.training.fooddelivery.dao.OrderDao;
import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;
import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.entity.Order;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.DriverMapper;
import com.ibc.training.fooddelivery.mapper.OrderMapper;
import com.ibc.training.fooddelivery.service.DriverService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;
    private final OrderDao orderDao;
    private final DriverMapper driverMapper;
    private final OrderMapper orderMapper;

    public DriverServiceImpl(DriverDao driverDao, OrderDao orderDao, DriverMapper driverMapper, OrderMapper orderMapper) {
        this.driverDao = driverDao;
        this.orderDao = orderDao;
        this.driverMapper = driverMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public DriverDTO createDriver(DriverDTO dto) {

        Driver driver = driverMapper.toEntity(dto);
        return driverMapper.toDto(driverDao.save(driver));
    }

    @Override
    public DriverDTO getDriverById(Integer driverId) {

        Driver driver = driverDao.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

        return driverMapper.toDto(driver);
    }

    @Override
    public List<DriverDTO> getAllDrivers() {

        return driverMapper.toDtoList(driverDao.findAll());
    }

    @Override
    public void deleteDriver(Integer driverId) {

        Driver driver = driverDao.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

        driverDao.deleteById(driver.getId());
    }

    // ✅ Assign driver to order
    @Override
    public OrderDTO assignDriverToOrder(Integer orderId, Integer driverId) {

        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Driver driver = driverDao.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

        order.setDriver(driver);

        return orderMapper.toDto(orderDao.save(order));
    }

    // ✅ Update location
    @Override
    public DriverDTO updateDriverLocation(Integer driverId, String location) {

        Driver driver = driverDao.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

       // driver.setLocation(location);

        return driverMapper.toDto(driverDao.save(driver));
    }

    // ✅ Get orders for driver
    @Override
    public List<OrderDTO> getOrdersByDriver(Integer driverId) {

        List<Order> orders = orderDao.findByDriverId(driverId);
        return orderMapper.toDtoList(orders);
    }
}