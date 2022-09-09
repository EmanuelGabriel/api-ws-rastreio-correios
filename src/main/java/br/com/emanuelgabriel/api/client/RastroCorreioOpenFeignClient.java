package br.com.emanuelgabriel.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.emanuelgabriel.api.config.ConfigOpenFeignClient;
import br.com.emanuelgabriel.api.dtos.response.RastroResponseDTO;

/**
 * 
 * @author emanuel.sousa
 *
 */

@FeignClient(name = "rastreio-correio", url = "${url.proxy.correios}", configuration = ConfigOpenFeignClient.class)
public interface RastroCorreioOpenFeignClient {

	@GetMapping(value = "/v1/sro-rastro/{codigoObjeto}", consumes = MediaType.APPLICATION_JSON_VALUE)
	RastroResponseDTO buscarRastroPorCodigoObjeto(@PathVariable(value = "codigoObjeto") String codigoObjeto);

}
