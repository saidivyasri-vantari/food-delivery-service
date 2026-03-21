package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.dto.OrderDTO;

import java.util.List;

public interface DriverService {

    DriverDTO createDriver(DriverDTO dto);

    DriverDTO getDriverById(Integer driverId);

    List<DriverDTO> getAllDrivers();

    void deleteDriver(Integer driverId);

    // ✅ Assign driver to order
    OrderDTO assignDriverToOrder(Integer orderId, Integer driverId);

    // ✅ Update driver location
    DriverDTO updateDriverLocation(Integer driverId, String location);

    // ✅ Get all orders assigned to driver
    List<OrderDTO> getOrdersByDriver(Integer driverId);
}