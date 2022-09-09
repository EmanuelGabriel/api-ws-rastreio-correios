package br.com.emanuelgabriel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiWsRastreioCorreiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWsRastreioCorreiosApplication.class, args);
	}

}
