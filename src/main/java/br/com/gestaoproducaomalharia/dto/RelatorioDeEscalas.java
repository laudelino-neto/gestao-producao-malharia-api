package br.com.gestaoproducaomalharia.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import lombok.Data;

@Data
public class RelatorioDeEscalas {

	private Colaborador colaborador;
	
	private Integer ano;
	
	private Integer mes;
	
	private List<Escala> escalas;
	
	private ResumoDeEscalas resumo;
	
	public RelatorioDeEscalas() {
		this.escalas = new ArrayList<>();
		this.resumo = new ResumoDeEscalas();
	}
	
}
