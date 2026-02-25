package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.infrastructure.exceptionhandler;

import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidOwnerException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.InvalidRestaurantNameException;
import com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception.NitDuplicateException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            errores.put(campo, error.getDefaultMessage());
        });
        return buildResponse(HttpStatus.BAD_REQUEST, "Error de validación", errores);
    }

    @ExceptionHandler(InvalidRestaurantNameException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidName(
            InvalidRestaurantNameException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(NitDuplicateException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateNit(
            NitDuplicateException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidOwnerException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidOwner(
            InvalidOwnerException ex) {
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), null);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String, Object>> handleFeignNotFound(FeignException.NotFound ex) {
        return buildResponse(HttpStatus.NOT_FOUND,
                "El usuario propietario no fue encontrado en el sistema", null);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignError(FeignException ex) {
        return buildResponse(HttpStatus.SERVICE_UNAVAILABLE,
                "Error al comunicarse con el servicio de usuarios", null);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status,
                                                              String mensaje,
                                                              Object detalles) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("message", mensaje);
        if (detalles != null) body.put("details", detalles);
        return ResponseEntity.status(status).body(body);
    }
}
