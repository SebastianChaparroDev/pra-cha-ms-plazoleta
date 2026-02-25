package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.output.feign;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.output.feign.dto.UserFeignResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pra-cha-ms-plazoleta", url = "${users.service.url}")
public interface IUserFeignCLient {

    @GetMapping("/api/v1/users/{id}")
    UserFeignResponseDto getUserById(@PathVariable("id") Long id);
}
