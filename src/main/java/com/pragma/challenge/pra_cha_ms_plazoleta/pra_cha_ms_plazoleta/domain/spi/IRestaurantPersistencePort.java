package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.Restaurant;

public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);
    boolean existByNit(String nit);
}
