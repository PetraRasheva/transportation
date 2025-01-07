package com.project.transportation.mapper;

import com.project.transportation.dto.GoodsDto;
import com.project.transportation.dto.PassengersDto;
import com.project.transportation.model.Goods;
import com.project.transportation.model.Passengers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransportableMapper {
    GoodsDto toDto(Goods entity);
    PassengersDto toDto(Passengers entity);
    Goods toEntity(GoodsDto dto);
    Passengers toEntity(PassengersDto dto);
}
