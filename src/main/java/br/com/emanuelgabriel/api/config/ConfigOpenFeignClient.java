package br.com.emanuelgabriel.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;


/**
 * 
 * @author emanuel.sousa
 *
 */

@Configuration
public class ConfigOpenFeignClient {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
