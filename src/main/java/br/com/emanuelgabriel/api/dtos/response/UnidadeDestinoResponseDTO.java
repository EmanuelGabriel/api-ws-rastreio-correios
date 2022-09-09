package br.com.emanuelgabriel.api.dtos.response;

import lombok.Data;

@Data
public class UnidadeDestinoResponseDTO {

	private EnderecoResponseDTO endereco;
	private String tipo;
	private String codSro;
	private String nome;

}
