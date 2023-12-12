package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LancamentoDeProducao {

	private Integer id;
	
	private Colaborador colaborador;
	
	private OrdemDeProducao ordem;
	
	private Produto produto;
	
	private Integer quantidade;
	
	private Tamanho tamanho;
	
	private LocalDateTime dataDeMovto;
	
}
