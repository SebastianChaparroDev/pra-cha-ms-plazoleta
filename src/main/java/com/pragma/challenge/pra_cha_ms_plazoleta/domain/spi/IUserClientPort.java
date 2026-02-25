package com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;

public interface IUserClientPort {

    User getUserById(Long id);
}
