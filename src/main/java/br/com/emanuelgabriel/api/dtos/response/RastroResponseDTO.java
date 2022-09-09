package br.com.emanuelgabriel.api.dtos.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Data
public class RastroResponseDTO {

	@JsonProperty(value = "objetos")
	private List<ObjetoResponseDTO> objetos = new ArrayList<>();
	private int quantidade;
	private String resultado;
	private String versao;

}
