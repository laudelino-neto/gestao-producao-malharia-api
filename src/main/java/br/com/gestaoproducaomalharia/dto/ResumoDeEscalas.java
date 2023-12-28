package br.com.gestaoproducaomalharia.dto;

import lombok.Data;

@Data
public class ResumoDeEscalas {

	private Integer qtdeParaRealizar;
	
	private Integer qtdeDeRealizadas;
	
	private Integer qtdeDeGeradas;
	
	private Integer qtdeDeFaltas;
	
	private Integer qtdeDeJustificadas;
	
	private Integer qtdeDeHoraExtra;
	
	private Integer qtdeDeAtrasos;
	
	private Integer saldoDeMinutos;
	
	public ResumoDeEscalas() {
		this.qtdeParaRealizar = 0;
		this.qtdeDeRealizadas = 0;
		this.qtdeDeGeradas = 0;
		this.qtdeDeFaltas = 0;
		this.qtdeDeJustificadas = 0;
		this.qtdeDeHoraExtra = 0;
		this.qtdeDeAtrasos = 0;
		this.saldoDeMinutos = 0;
	}
	
}
