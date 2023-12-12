package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
import lombok.Data;

@Data
public class Escala {
	
	private Integer id;

	private Colaborador colaborador;
	
	private LocalDate data;
	
	private LocalTime entrada;
	
	private LocalTime saida;
	
	private Confirmacao isRealizada;
	
}
