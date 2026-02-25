package com.pragma.challenge.pra_cha_ms_plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PraChaMsPlazoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraChaMsPlazoletaApplication.class, args);
	}

}
