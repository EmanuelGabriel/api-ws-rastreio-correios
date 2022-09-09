package br.com.emanuelgabriel.api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author emanuel.sousa
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CodigoObjetoRastroNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CodigoObjetoRastroNaoEncontrado(String mensagem) {
		super(mensagem);
	}

}
