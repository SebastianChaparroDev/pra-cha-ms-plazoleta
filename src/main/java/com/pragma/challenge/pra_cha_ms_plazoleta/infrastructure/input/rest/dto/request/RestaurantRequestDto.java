package com.pragma.challenge.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequestDto {

    @NotBlank(message = "El nombre del restaurante es obligatorio")
    private String name;

    @NotBlank(message = "El NIT es obligatorio")
    @Pattern(regexp = "\\d+", message = "El NIT debe ser únicamente numérico")
    private String nit;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
            regexp = "^\\+?\\d{1,13}$",
            message = "El teléfono debe ser máximo 13 caracteres y puede contener el símbolo +"
    )
    private String phone;

    @NotBlank(message = "La URL del logo es obligatoria")
    private String urlLogo;

    @NotNull(message = "El id del propietario es obligatorio")
    private Long idOwner;
}
