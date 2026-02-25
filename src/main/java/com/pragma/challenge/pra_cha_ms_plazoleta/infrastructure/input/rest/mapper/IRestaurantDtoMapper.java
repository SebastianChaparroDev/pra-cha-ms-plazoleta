package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.mapper;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request.RestaurantRequestDto;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.response.RestaurantResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "nit", target = "nit")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "urlLogo", target = "urlLogo")
    @Mapping(source = "idOwner", target = "idOwner")
    Restaurant toRestaurant(RestaurantRequestDto dto);
    RestaurantResponseDto toResponseDto(Restaurant restaurant);
}
