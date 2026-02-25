package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.usecase;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidOwnerException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidRestaurantNameException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.NitDuplicateException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.User;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi.IUserClientPort;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserClientPort userClientPort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserClientPort userClientPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userClientPort = userClientPort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        if (!restaurant.isValidName()){
            throw new InvalidRestaurantNameException("Nombre de restaurante invalido, no puede ser unicamente numerico");
        }
        if (restaurantPersistencePort.existsByNit(restaurant.getNit())) {
            throw new NitDuplicateException("Ya existe un restaurante registrado con este NIT");
        }
        User owner = userClientPort.getUserById(restaurant.getIdOwner());
        if (!owner.isOwner()) {
            throw new InvalidOwnerException("El usuario con ID " + restaurant.getIdOwner() + "no tiene el rol propietario");
        }

        restaurantPersistencePort.saveRestaurant(restaurant);
    }
}
