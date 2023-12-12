package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.TipoDeAcerto;
import lombok.Data;

@Data
public class AcertoDeEscala {

	private Integer id;
	
	private Colaborador colaborador;
	
	private Integer tempo;
	
	private TipoDeAcerto tipo;
	
}
