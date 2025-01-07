package com.project.transportation.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type" // This will be the key in the JSON that holds the type information
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GoodsDto.class, name = "goods"),
        @JsonSubTypes.Type(value = PassengersDto.class, name = "passengers")
})
public sealed interface TransportableDto permits GoodsDto, PassengersDto {
    String name();
}
