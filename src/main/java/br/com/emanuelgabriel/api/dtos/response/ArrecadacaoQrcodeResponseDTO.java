package br.com.emanuelgabriel.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadacaoQrcodeResponseDTO {

	private String codigoConciliacaoSolicitante;

	private String emailDevedor;

	private int codigoPaisTelefoneDevedor;

	private int numeroTelefoneDevedor;

	private String timestampCriacaoSolicitacao;

	private int quantidadeSegundoExpiracao;

	private int numeroVersaoSolicitacaoPagamento;

	private int cpfDevedor;

	private int cnpjDevedor;

	private String nomeDevedor;

	private long valorOriginalSolicitacao;

	private String estadoSolicitacao;

	private String linkQrCode;

	private String qrCode;

}
