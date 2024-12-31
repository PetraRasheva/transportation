package com.project.transportation.mapper;

import com.project.transportation.dto.*;
import com.project.transportation.model.Goods;
import com.project.transportation.model.Passengers;
import com.project.transportation.model.Transportable;
import com.project.transportation.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportationMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "transportable.id", target = "transportableId")
    TransportationDto toDto(Transportation transportation);

    @Mapping(source = "companyId", target = "company.id")
    Transportation toEntity(CreateTransportationDto createTransportationDto);

    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    @Mapping(target = "company", ignore = true) // Prevent overwriting related entities
    void updateTransportationFromDto(TransportationDto dto, @MappingTarget Transportation entity);

    // Method to map Transportable entity to the correct TransportableDto (either GoodsDto or PassengersDto)
//    default TransportableDto mapTransportable(Transportable transportable) {
//        if (transportable instanceof Goods) {
//            Goods goods = (Goods) transportable;
//            return new GoodsDto(goods.getName(), goods.getWeight(), goods.getPricePerKg());
//        } else if (transportable instanceof Passengers) {
//            Passengers passengers = (Passengers) transportable;
//            return new PassengersDto(passengers.getName(), passengers.getCount(), passengers.getBaseFare());
//        } else {
//            throw new IllegalArgumentException("Unknown transportable type");
//        }
//    }
//
//    // Method to map TransportableDto to the correct Transportable entity (either Goods or Passengers)
//    default Transportable mapTransportableEntity(TransportableDto transportableDto) {
//        if (transportableDto instanceof GoodsDto) {
//            GoodsDto goodsDto = (GoodsDto) transportableDto;
//            Goods goods = new Goods();
//            goods.setName(goodsDto.name());
//            goods.setWeight(goodsDto.weight());
//            goods.setPricePerKg(goodsDto.pricePerKg());
//            return goods;
//        } else if (transportableDto instanceof PassengersDto) {
//            PassengersDto passengersDto = (PassengersDto) transportableDto;
//            Passengers passengers = new Passengers();
//            passengers.setName(passengersDto.name());
//            passengers.setCount(passengersDto.passengerCount());
//            return passengers;
//        } else {
//            throw new IllegalArgumentException("Unknown transportable type");
//        }
//    }
}
