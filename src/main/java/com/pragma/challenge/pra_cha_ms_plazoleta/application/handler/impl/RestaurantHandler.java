package com.pragma.challenge.pra_cha_ms_plazoleta.application.handler.impl;

import com.pragma.challenge.pra_cha_ms_plazoleta.application.handler.IRestaurantHandler;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request.RestaurantRequestDto;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.mapper.IRestaurantDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantDtoMapper restaurantDtoMapper;

    public RestaurantHandler(IRestaurantServicePort restaurantServicePort, IRestaurantDtoMapper restaurantDtoMapper) {
        this.restaurantServicePort = restaurantServicePort;
        this.restaurantDtoMapper = restaurantDtoMapper;
    }

    @Override
    public void createRestaurant(RestaurantRequestDto request) {
        Restaurant restaurant = restaurantDtoMapper.toRestaurant(request);
        restaurantServicePort.createRestaurant(restaurant);
    }
}
