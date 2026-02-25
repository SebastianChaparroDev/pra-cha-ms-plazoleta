package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.configuration;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi.IUserClientPort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.usecase.RestaurantUseCase;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.IUserFeignCLient;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.impl.UserClientAdapter;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.jpa.adapter.RestaurantPersistenceAdater;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(
            IRestaurantRepository restaurantRepository,
            IRestaurantEntityMapper restaurantEntityMapper) {
        return new RestaurantPersistenceAdater(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IUserClientPort userClientPort(IUserFeignCLient userFeignCLient) {
        return new UserClientAdapter(userFeignCLient);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(
            IRestaurantPersistencePort restaurantPersistencePort,
            IUserClientPort userClientPort) {
        return  new RestaurantUseCase(restaurantPersistencePort, userClientPort);
    }
}
