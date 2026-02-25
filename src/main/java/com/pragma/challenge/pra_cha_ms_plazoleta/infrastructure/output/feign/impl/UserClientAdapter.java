package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.impl;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi.IUserClientPort;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.IUserFeignCLient;
import com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.dto.UserFeignResponseDto;

public class UserClientAdapter implements IUserClientPort {

    private final IUserFeignCLient userFeignCLient;

    public UserClientAdapter(IUserFeignCLient userFeignCLient) {
        this.userFeignCLient = userFeignCLient;
    }

    @Override
    public User getUserById(Long id) {
        UserFeignResponseDto response = userFeignCLient.getUserById(id);
        User user = new User();
        user.setId(response.getId());
        user.setRole(response.getRole());
        return user;
    }
}
