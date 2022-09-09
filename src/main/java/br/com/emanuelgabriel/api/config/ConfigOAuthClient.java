package br.com.emanuelgabriel.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigOAuthClient {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigOAuthClient.class);

	@Value("${clientId}")
	private String clientId;

	@Value("${clientSecret}")
	private String clientSecret;

	@Value("${tokenUrl}")
	private String tokenUrl;

}
