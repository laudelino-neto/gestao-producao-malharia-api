package br.com.gestaoproducaomalharia.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import lombok.Data;

@Data
public class ParametrosDeGeracao {

	private Colaborador colaborador;
	
	private LocalDate dataInicial;
	
	private LocalDate dataFinal;
	
	private LocalTime entrada;
	
	private LocalTime saida;
	
}
