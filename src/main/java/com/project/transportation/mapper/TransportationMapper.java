package com.project.transportation.mapper;

import com.project.transportation.dto.*;
import com.project.transportation.model.Goods;
import com.project.transportation.model.Passengers;
import com.project.transportation.model.Transportable;
import com.project.transportation.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TransportableMapper.class)
public interface TransportationMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "paid", target = "isPaid")
    @Mapping(source = "transportable", target = "transportableDto")
    TransportationDto toDto(Transportation transportation);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "driverId", target = "driver.id")
    @Mapping(source = "isPaid", target = "paid")
    @Mapping(target = "transportable", ignore = true)  // Ignore transportable in DTO (it's a complex field)
    Transportation toEntity(CreateTransportationDto createTransportationDto);

    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "transportable", source = "transportableDto")
    @Mapping(source = "isPaid", target = "paid")
    void updateTransportationFromDto(TransportationDto dto, @MappingTarget Transportation entity);

    default TransportableDto mapTransportableToDto(Transportable transportable) {
        if (transportable == null) {
            return null;  // handle null transportable case
        }
        if (transportable instanceof Goods) {
            Goods goods = (Goods) transportable;
            return new GoodsDto(goods.getName(), goods.getWeight(), goods.getPricePerKg());
        } else if (transportable instanceof Passengers) {
            Passengers passengers = (Passengers) transportable;
            return new PassengersDto(passengers.getName(), passengers.getBaseFare(), passengers.getCount());
        } else {
            throw new IllegalArgumentException("Unknown transportable type: " + transportable.getClass().getSimpleName());
        }
    }

    default Transportable mapTransportableDtoToEntity(TransportableDto transportableDto) {
        if (transportableDto instanceof GoodsDto) {
            GoodsDto goodsDto = (GoodsDto) transportableDto;
            return new Goods(goodsDto.name(), goodsDto.weight(), goodsDto.pricePerKg());
        } else if (transportableDto instanceof PassengersDto) {
            PassengersDto passengersDto = (PassengersDto) transportableDto;
            return new Passengers(passengersDto.name(), passengersDto.baseFare(), passengersDto.count());
        } else {
            throw new IllegalArgumentException("Unknown transportable type: " + transportableDto.getClass().getSimpleName());
        }
    }
}
