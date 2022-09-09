package br.com.emanuelgabriel.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class UnidadeResponseDTO {

	private EnderecoResponseDTO endereco;

	private String tipo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String codSro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;

}
