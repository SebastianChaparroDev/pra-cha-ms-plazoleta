package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.usecase;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidOwnerException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidRestaurantNameException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.NitDuplicateException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.Restaurant;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.model.User;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.spi.IUserClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort persistencePort;

    @Mock
    private IUserClientPort userClientPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private Restaurant validRestaurant;
    private User validOwner;

    @BeforeEach
    void setUp() {
        validRestaurant = new Restaurant();
        validRestaurant.setName("El Buen Sabor");
        validRestaurant.setNit("123456789");
        validRestaurant.setAddress("Calle 123 # 45-67");
        validRestaurant.setPhone("+573005698325");
        validRestaurant.setUrlLogo("https://logo.com/img.png");
        validRestaurant.setIdOwner(1L);

        validOwner = new User();
        validOwner.setId(1L);
        validOwner.setRole("OWNER");
    }

    @Test
    @DisplayName("Debe crear restaurante exitosamente con datos válidos")
    void createRestaurantValidData() {
        when(persistencePort.existsByNit(any())).thenReturn(false);
        when(userClientPort.getUserById(1L)).thenReturn(validOwner);

        assertDoesNotThrow(() -> restaurantUseCase.createRestaurant(validRestaurant));
        verify(persistencePort, times(1)).saveRestaurant(validRestaurant);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el nombre es únicamente numérico")
    void createRestaurantOnlyNumbers() {
        validRestaurant.setName("12345");

        assertThrows(InvalidRestaurantNameException.class,
                () -> restaurantUseCase.createRestaurant(validRestaurant));

        verify(persistencePort, never()).saveRestaurant(any());
    }

    @Test
    @DisplayName("Debe permitir nombre con letras y números mezclados")
    void createRestaurantValidName() {
        validRestaurant.setName("Restaurante 123");

        when(persistencePort.existsByNit(any())).thenReturn(false);
        when(userClientPort.getUserById(1L)).thenReturn(validOwner);

        assertDoesNotThrow(() -> restaurantUseCase.createRestaurant(validRestaurant));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el NIT ya está registrado")
    void createRestaurantDuplicateNit() {
        when(persistencePort.existsByNit("123456789")).thenReturn(true);

        assertThrows(NitDuplicateException.class,
                () -> restaurantUseCase.createRestaurant(validRestaurant));

        verify(persistencePort, never()).saveRestaurant(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el usuario no tiene rol PROPIETARIO")
    void createRestaurantInvalidRoleOwner() {
        User usuarioCliente = new User();
        usuarioCliente.setId(1L);
        usuarioCliente.setRole("CLIENTE");

        when(persistencePort.existsByNit(any())).thenReturn(false);
        when(userClientPort.getUserById(1L)).thenReturn(usuarioCliente);

        assertThrows(InvalidOwnerException.class,
                () -> restaurantUseCase.createRestaurant(validRestaurant));

        verify(persistencePort, never()).saveRestaurant(any());
    }

    @Test
    @DisplayName("La validación del nombre tiene precedencia sobre las demás")
    void createRestaurantInvalidNitThrowNameFirst() {
        validRestaurant.setName("9999");

        assertThrows(InvalidRestaurantNameException.class,
                () -> restaurantUseCase.createRestaurant(validRestaurant));

        verify(persistencePort, never()).existsByNit(any());
        verify(userClientPort, never()).getUserById(any());
    }
}