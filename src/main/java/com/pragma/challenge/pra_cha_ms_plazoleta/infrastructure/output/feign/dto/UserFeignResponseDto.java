package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.output.feign.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFeignResponseDto {

    private Long id;
    private String role;

}
