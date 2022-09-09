package br.com.emanuelgabriel.api.dtos.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoResponseDTO {

	private String codigo;
	private String descricao;
	private Date dtHrCriado;
	private String tipo;
	private UnidadeResponseDTO unidade;
	private UnidadeDestinoResponseDTO unidadeDestino;
	private String urlIcone;

}
