package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrdemDeProducao {

	private Integer id;
	
	private Fornecedor fornecedor;
	
	private LocalDate dataDoPedido;
	
	private LocalDate dataDeEntrega;
	
	private Integer totalDeItens;
	
}
