package br.com.emanuelgabriel.api.dtos.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ObjetoResponseDTO {

	private String codObjeto;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mensagem;

	private List<EventoResponseDTO> eventos = new ArrayList<>();
	private String modalidade;
	private TipoPostalResponseDTO tipoPostal;
	private boolean habilitaAutoDeclaracao;
	private boolean permiteEncargoImportacao;
	private boolean habilitaPercorridaCarteiro;
	private boolean bloqueioObjeto;
	private boolean possuiLocker;
	private boolean habilitaLocker;
	private boolean habilitaCrowdshipping;

}
