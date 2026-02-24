package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;

    public boolean isValidName(){
        if (name == null || name.isBlank()){
            return false;
        }
        return !name.trim().matches("\\d+");
    }
}
