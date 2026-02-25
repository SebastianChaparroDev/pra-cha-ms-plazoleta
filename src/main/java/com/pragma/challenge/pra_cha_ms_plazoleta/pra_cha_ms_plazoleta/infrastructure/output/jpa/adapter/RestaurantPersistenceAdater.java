package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;

public class RestaurantPersistenceAdater implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    public RestaurantPersistenceAdater(IRestaurantRepository restaurantRepository, IRestaurantEntityMapper restaurantEntityMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public boolean existsByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }
}
