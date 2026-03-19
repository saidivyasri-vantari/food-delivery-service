package com.ibc.training.fooddelivery.service;

import com.ibc.training.fooddelivery.dao.FavoriteDao;
import com.ibc.training.fooddelivery.dto.FavoriteDTO;
import com.ibc.training.fooddelivery.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteDao dao;
    private final FavoriteMapper mapper;

    public FavoriteService(FavoriteDao dao, FavoriteMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public FavoriteDTO addFavorite(FavoriteDTO dto) {

        dao.findByCustomerIdAndRestaurantId(dto.getCustomerId(), dto.getRestaurantId())
                .ifPresent(f -> {
                    throw new RuntimeException("Already added to favorites");
                });

        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    public List<FavoriteDTO> getFavorites(Long customerId) {
        return mapper.toDtoList(dao.findByCustomerId(customerId));
    }
}