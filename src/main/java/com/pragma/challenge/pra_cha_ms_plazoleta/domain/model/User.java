package com.pragma.challenge.pra_cha_ms_plazoleta.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String role;

    public boolean isOwner(){
        return "OWNER".equals(role);
    }
}
