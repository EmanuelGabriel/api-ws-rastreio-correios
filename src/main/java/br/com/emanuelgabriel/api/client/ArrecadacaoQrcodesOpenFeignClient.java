package br.com.emanuelgabriel.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.emanuelgabriel.api.config.ConfigOpenFeignClient;
import br.com.emanuelgabriel.api.dtos.response.ArrecadacaoQrcodeResponseDTO;

/**
 * 
 * @author emanuel.sousa
 *
 */

@FeignClient(name = "arrecadacao-qrcodes", url = "${url.base.api.bb.sandbox}", configuration = ConfigOpenFeignClient.class)
public interface ArrecadacaoQrcodesOpenFeignClient {

	@GetMapping(value = "/arrecadacao-qrcodes", produces = MediaType.APPLICATION_JSON_VALUE)
	ArrecadacaoQrcodeResponseDTO buscarQrcodePagamentoInstantaneo(
			@RequestParam(value = "gw-dev-app-key") String chaveAppDeveloper,
			@RequestParam(value = "numeroConvenio") Integer numeroConvenio, 
			@RequestParam(value = "codigoGuiaRecebimento") String codigoGuiaRecebimento);
	
	
}
