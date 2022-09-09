package br.com.emanuelgabriel.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.api.dtos.response.ArrecadacaoQrcodeResponseDTO;
import br.com.emanuelgabriel.api.service.ArrecadacaoQrcodeService;

/**
 * 
 * @author emanuel.sousa
 *
 */

@RestController
@RequestMapping(value = "/arrecadacao-qrcode", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArrecadacaoQrcodePagamentoInstantaneoController {

	private static final Logger LOG = LoggerFactory.getLogger(ArrecadacaoQrcodePagamentoInstantaneoController.class);

	private final ArrecadacaoQrcodeService arrecadacaoQrcodeService;

	public ArrecadacaoQrcodePagamentoInstantaneoController(ArrecadacaoQrcodeService arrecadacaoQrcodeService) {
		this.arrecadacaoQrcodeService = arrecadacaoQrcodeService;
	}
	
	@GetMapping
	public ResponseEntity<ArrecadacaoQrcodeResponseDTO> buscarQrcodePagamentoInstantaneo(
			@Value("${chave.gw.dev.app.key}") String chaveAppDeveloper, 
			Integer numeroConvenio, 
			String codigoGuiaRecebimento){
		LOG.info("GET /arrecadacao-qrcode - {};{};{}", chaveAppDeveloper, numeroConvenio, codigoGuiaRecebimento);
		var retorno = arrecadacaoQrcodeService.buscarArrecadacaoQrCodePorPagamentoInstantaneo(chaveAppDeveloper, numeroConvenio, codigoGuiaRecebimento);
		return retorno != null ? ResponseEntity.ok().body(retorno) : ResponseEntity.notFound().build();
	}
	
	
}
