package br.com.emanuelgabriel.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.api.client.ArrecadacaoQrcodesOpenFeignClient;
import br.com.emanuelgabriel.api.dtos.response.ArrecadacaoQrcodeResponseDTO;
import feign.FeignException;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Service
public class ArrecadacaoQrcodeService {

	private static final Logger LOG = LoggerFactory.getLogger(ArrecadacaoQrcodeService.class);

	@Value("${chave.gw.dev.app.key}")
	private String chaveAppDeveloper;
	
	private final ArrecadacaoQrcodesOpenFeignClient client;


	public ArrecadacaoQrcodeService(ArrecadacaoQrcodesOpenFeignClient client) {
		this.client = client;
	}
	
	
	public ArrecadacaoQrcodeResponseDTO buscarArrecadacaoQrCodePorPagamentoInstantaneo(String chaveAppDeveloper, Integer numeroConvenio, String codigoGuiaRecebimento) {
		LOG.info("Consultar Arrecadação de QRCode por Pagamento Instantâneo {};{};{}", chaveAppDeveloper, numeroConvenio, codigoGuiaRecebimento);
		
		ArrecadacaoQrcodeResponseDTO retorno = null;
		chaveAppDeveloper = this.chaveAppDeveloper;

		try {

			retorno = client.buscarQrcodePagamentoInstantaneo(chaveAppDeveloper, numeroConvenio, codigoGuiaRecebimento);
			LOG.info("{}", retorno);

		} catch (FeignException ex) {
			if (ex.status() == 401) {
				LOG.info("Não autorizado - 401 Unauthorized: {}", ex.getMessage());
			} else if (ex.status() == -1 && ex.getMessage().contains("Read timed out executing")) {
				LOG.info("Erro de timed out: {}", ex.getMessage());
			}
			
			LOG.info("URL: {}", ex.request().url());
			LOG.info("Body: {}", ex.request().body());
			LOG.info("Request: {}", ex.request().requestTemplate());
			
		}

		return retorno;
	}
	
}
