package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.Status;
import lombok.Data;

@Data
public class Produto {

	private Integer id;
	
	private String descricao;
	
	private Status status;
	
}
