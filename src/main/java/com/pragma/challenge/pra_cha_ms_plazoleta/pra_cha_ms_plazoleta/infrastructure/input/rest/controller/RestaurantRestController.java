package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.input.rest.controller;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.application.handler.IRestaurantHandler;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.input.rest.dto.request.RestaurantRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurant")
@Tag(name = "Restaurant", description = "Servicio para la gestión de restaurantes de la plazoleta")
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    public RestaurantRestController(IRestaurantHandler restaurantHandler) {
        this.restaurantHandler = restaurantHandler;
    }

    @PostMapping
    @Operation(
            summary = "Crear restaurante",
            description = "Permite al administrador crear un restaurante. " +
                    "El propietario debe tener rol PROPIETARIO en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Propietario no encontrado"),
            @ApiResponse(responseCode = "409", description = "El NIT ya está registrado"),
            @ApiResponse(responseCode = "422", description = "El usuario no tiene rol de propietario")
    })
    public ResponseEntity<Void> crearRestaurante(@Valid @RequestBody RestaurantRequestDto request) {
        restaurantHandler.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
