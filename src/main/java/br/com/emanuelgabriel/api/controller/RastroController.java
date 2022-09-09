package br.com.emanuelgabriel.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.api.dtos.response.RastroResponseDTO;
import br.com.emanuelgabriel.api.service.RastroCorreiosService;

/**
 * 
 * @author emanuel.sousa
 *
 */

@RestController
@RequestMapping(value = "/v1/rastros", produces = MediaType.APPLICATION_JSON_VALUE)
public class RastroController {

	private static final Logger LOG = LoggerFactory.getLogger(RastroController.class);

	private final RastroCorreiosService rastroCorreiosService;

	public RastroController(RastroCorreiosService rastroCorreiosService) {
		this.rastroCorreiosService = rastroCorreiosService;
	}

	@GetMapping(value = "{codigoObjeto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RastroResponseDTO> buscarRastroCorreiosPorCodigoObjeto(@PathVariable(value = "codigoObjeto") String codigoObjeto) {
		LOG.info("GET /api/v1/rastros/{}", codigoObjeto);
		var rastroCodObjeto = rastroCorreiosService.buscarRastroPorCodigoObjeto(codigoObjeto);
		return rastroCodObjeto != null ? ResponseEntity.ok().body(rastroCodObjeto) : ResponseEntity.notFound().build();
	}

}
