package br.com.emanuelgabriel.api;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.emanuelgabriel.api.config.ConfigOAuthClient;


/**
 * 
 * @author emanuel.sousa
 *
 */

public class Autenticacao {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigOAuthClient.class);
	
	private static final String URL_BASE_OAUTH2_SANDBOX = "https://oauth.sandbox.bb.com.br";
	
	private static final String URL_BASE_API_SANDBOX = "https://api.sandbox.bb.com.br/pix-bb/v1";
	
	private static final String GET_HEADER_BASIC_AUTHORIZATION = "Basic ZXlKcFpDSTZJamhqT1RKaE16Z3RaamxqTXkwME1EbG1MVGd3TURjdFlXWmxJaXdpWTI5a2FXZHZVSFZpYkdsallXUnZjaUk2TUN3aVkyOWthV2R2VTI5bWRIZGhjbVVpT2pReE5UTTVMQ0p6WlhGMVpXNWphV0ZzU1c1emRHRnNZV05oYnlJNk1YMDpleUpwWkNJNkltWTJNMll3TTJRdFpHUTVPUzAwTkRrMkxUZzVOVFV0T0dJNFpqaGlNemRqWVRRek5ESTVZeUlzSW1OdlpHbG5iMUIxWW14cFkyRmtiM0lpT2pBc0ltTnZaR2xuYjFOdlpuUjNZWEpsSWpvME1UVXpPU3dpYzJWeGRXVnVZMmxoYkVsdWMzUmhiR0ZqWVc4aU9qRXNJbk5sY1hWbGJtTnBZV3hEY21Wa1pXNWphV0ZzSWpveExDSmhiV0pwWlc1MFpTSTZJbWh2Ylc5c2IyZGhZMkZ2SWl3aWFXRjBJam94TmpZd01qSTJOREEyTWpNMmZR";
	
	private static final String GET_CLIENT_ID = "eyJpZCI6IjhjOTJhMzgtZjljMy00MDlmLTgwMDctYWZlIiwiY29kaWdvUHVibGljYWRvciI6MCwiY29kaWdvU29mdHdhcmUiOjQxNTM5LCJzZXF1ZW5jaWFsSW5zdGFsYWNhbyI6MX0";
	
	private static final String GET_CLIENT_SECRET = "eyJpZCI6ImY2M2YwM2QtZGQ5OS00NDk2LTg5NTUtOGI4ZjhiMzdjYTQzNDI5YyIsImNvZGlnb1B1YmxpY2Fkb3IiOjAsImNvZGlnb1NvZnR3YXJlIjo0MTUzOSwic2VxdWVuY2lhbEluc3RhbGFjYW8iOjEsInNlcXVlbmNpYWxDcmVkZW5jaWFsIjoxLCJhbWJpZW50ZSI6ImhvbW9sb2dhY2FvIiwiaWF0IjoxNjYwMjI2NDA2MjM2fQ";
	
	private static final String GET_GRANT_TYPE = "client_credentials";
	
	private static final String GET_SCOPE = "pix.arrecadacao-requisicao pix.arrecadacao-info";
	
	private static final String GET_CHAVE_APP_DEVELOPER = "d27b37790cffab601363e17d00050356b901a5b8";
	
	@Value("${value.token}")
    private static String getToken;
	
	private static RestTemplate restTemplate;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		restTemplate = new RestTemplate();

		try {
			
		
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("client_id", GET_CLIENT_ID);
			map.add("client_secret", GET_CLIENT_SECRET);
			map.add("grant_type", GET_GRANT_TYPE);
			map.add("scope", GET_SCOPE);
			
			
			var httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.set("Authorization", GET_HEADER_BASIC_AUTHORIZATION);
			var httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
			
			LOG.info("Headers: {}", httpHeaders);
			LOG.info("HttpEntity: {}", httpEntity);
			
			String body = restTemplate.postForEntity(URL_BASE_OAUTH2_SANDBOX.concat("/oauth/token?developer_application_key=".concat(GET_CHAVE_APP_DEVELOPER)), httpEntity , String.class).getBody();
			
			System.out.println(body);
			
			LOG.info("Body: {}", body);
			
			var objMapper = new ObjectMapper();
			var jsonNodeBody = objMapper.readValue(body, JsonNode.class);
			
			getToken = jsonNodeBody.get("access_token").asText();
			LOG.info("Token: {}", getToken);		

		} catch (JsonProcessingException | RestClientException e) {
			LOG.info("Erro: {}", e.getMessage());
		}
		
		new Autenticacao().testarGETarrecadacaoQrCodes();
		
		new Autenticacao().testarGETarrecadacaoQrCodesPagamentos();
		
	}
	
	/**
	 * 
	 */
	public void testarGETarrecadacaoQrCodes() {
		LOG.info("Chamando o GET testar");
		
		try {
		
			var httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        httpHeaders.set("Authorization", "Bearer ".concat(getToken));
	        
	        var httpEntity = new HttpEntity<>(httpHeaders);
	        var response = restTemplate.exchange(URL_BASE_API_SANDBOX
	        		.concat("/arrecadacao-qrcodes?gw-dev-app-key=d27b37790cffab601363e17d00050356b901a5b8"
	        		.concat("&")
	        		.concat("numeroConvenio=62191")
	        		.concat("&")
	        		.concat("codigoGuiaRecebimento=83660000000199800053846101173758000000000018")), HttpMethod.GET, httpEntity, String.class);
	        
	        LOG.info("Resposta: {}", response);
	        LOG.info("Resposta: {}", response.getBody());
			
		} catch (RestClientException e) {
			LOG.info("Erro: {}", e.getMessage());
		}
		
		
	}
	
	/**
	 * 
	 */
	public void testarGETarrecadacaoQrCodesPagamentos() {
		LOG.info("Chamando o GET Arrecadação QRCodes de Pagamentos");
		
		String cod = "E0000000020210520172412581438073";
		
		try {
			
			var httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        httpHeaders.set("Authorization", "Bearer ".concat(getToken));
	       
	        var httpEntity = new HttpEntity<>(httpHeaders);
	        var response = restTemplate.exchange(URL_BASE_API_SANDBOX
	        		.concat("/arrecadacao-qrcodes/pagamentos/".concat(cod).concat("?gw-dev-app-key=".concat(GET_CHAVE_APP_DEVELOPER))), HttpMethod.GET, httpEntity, String.class);
	        
	        LOG.info("Resposta: {}", response);
	        LOG.info("Resposta: {}", response.getBody());
	        
		} catch (RestClientException e) {
			LOG.info("Erro: {}", e.getMessage());
		}
		
	}

}
