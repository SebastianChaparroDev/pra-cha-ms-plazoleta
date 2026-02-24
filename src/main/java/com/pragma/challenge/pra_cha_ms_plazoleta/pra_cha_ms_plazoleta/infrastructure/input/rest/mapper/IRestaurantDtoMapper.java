package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.input.rest.mapper;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request.RestaurantRequestDto;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.response.RestaurantResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantDtoMapper {
    Restaurant toRestaurant(RestaurantRequestDto dto);
    RestaurantResponseDto toResponseDto(Restaurant restaurant);
}
