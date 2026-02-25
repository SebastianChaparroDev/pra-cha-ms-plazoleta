package com.pragma.challenge.pra_cha_ms_plazoleta.application.handler;

import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto request);
}
