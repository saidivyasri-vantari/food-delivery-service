package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.DeliveryAreaDao;
import com.ibc.training.fooddelivery.dto.DeliveryAreaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAreaService {

    private final DeliveryAreaDao dao;
    private final DeliveryAreaMapper mapper;

    public DeliveryAreaService(DeliveryAreaDao dao, DeliveryAreaMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<DeliveryAreaDTO> getByRestaurant(Long restaurantId) {
        return mapper.toDtoList(dao.findByRestaurantId(restaurantId));
    }
}