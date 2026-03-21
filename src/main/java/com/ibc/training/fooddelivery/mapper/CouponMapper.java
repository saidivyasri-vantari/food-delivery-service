package com.ibc.training.fooddelivery.mapper;

import com.ibc.training.fooddelivery.dto.CouponDTO;
import com.ibc.training.fooddelivery.entity.Coupon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    CouponDTO toDto(Coupon coupon);

    Coupon toEntity(CouponDTO dto);

    List<CouponDTO> toDtoList(List<Coupon> coupons);
}