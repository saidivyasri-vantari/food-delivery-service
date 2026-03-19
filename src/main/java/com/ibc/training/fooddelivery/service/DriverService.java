package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.DriverDao;
import com.ibc.training.fooddelivery.dto.DriverDTO;
import com.ibc.training.fooddelivery.entity.Driver;
import com.ibc.training.fooddelivery.exception.ResourceNotFoundException;
import com.ibc.training.fooddelivery.mapper.DriverMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverDao dao;
    private final DriverMapper mapper;

    public DriverService(DriverDao dao, DriverMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    // ✅ CREATE
    public DriverDTO create(DriverDTO dto) {
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    // ✅ GET BY ID (FIXED)
    public DriverDTO getDriver(Long id) {
        return mapper.toDto(
                dao.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Driver not found with id: " + id))
        );
    }

    // ✅ GET ALL
    public List<DriverDTO> getAll() {
        return mapper.toDtoList(dao.findAll());
    }

    // ✅ UPDATE (ADDED)
    public DriverDTO updateDriver(Long id, DriverDTO dto) {

        Driver driver = dao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found with id: " + id));

        driver.setName(dto.getName());
        driver.setLocation(dto.getLocation());

        return mapper.toDto(dao.save(driver));
    }

    // ✅ DELETE (ADDED)
    public void deleteDriver(Long id) {

        Driver driver = dao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver not found with id: " + id));

        dao.deleteById(driver.getId());
    }
}