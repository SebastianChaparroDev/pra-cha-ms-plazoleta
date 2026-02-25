package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.jpa.repository;

import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    boolean existsByNit(String nit);
}
