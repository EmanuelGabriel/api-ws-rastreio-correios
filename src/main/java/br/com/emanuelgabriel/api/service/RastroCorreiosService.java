package br.com.emanuelgabriel.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.api.client.RastroCorreioOpenFeignClient;
import br.com.emanuelgabriel.api.dtos.response.ObjetoResponseDTO;
import br.com.emanuelgabriel.api.dtos.response.RastroResponseDTO;
import br.com.emanuelgabriel.api.service.exception.CodigoObjetoRastroNaoEncontrado;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Service
public class RastroCorreiosService {

	private static final Logger LOG = LoggerFactory.getLogger(RastroCorreiosService.class);

	private final RastroCorreioOpenFeignClient client;

	public RastroCorreiosService(RastroCorreioOpenFeignClient client) {
		this.client = client;
	}

	public RastroResponseDTO buscarRastroPorCodigoObjeto(String codigoObjeto) {
		LOG.info("Buscar rastro dos correios por código do objeto: {}", codigoObjeto);

		var codigoRastroResponseDTO = client.buscarRastroPorCodigoObjeto(codigoObjeto);

		for (ObjetoResponseDTO obj : codigoRastroResponseDTO.getObjetos()) {

			if (obj.getMensagem() != null) {
				throw new CodigoObjetoRastroNaoEncontrado("Código de Objeto de rastreio não encontrado");
			}
		}

		LOG.info("CódigoObjeto: {}", codigoRastroResponseDTO.getObjetos().get(0).getCodObjeto());

		return codigoRastroResponseDTO;

	}

}
