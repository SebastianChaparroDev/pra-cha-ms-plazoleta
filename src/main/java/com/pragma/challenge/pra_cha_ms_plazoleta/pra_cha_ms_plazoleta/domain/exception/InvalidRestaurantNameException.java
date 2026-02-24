package com.pragma.challenge.pra_cha_ms_plazoleta.pra_cha_ms_plazoleta.domain.exception;

public class InvalidRestaurantNameException extends RuntimeException{
    public InvalidRestaurantNameException(String message){
        super(message);
    }
}
