package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.Status;
import lombok.Data;

@Data
public class Colaborador {

	private Integer id;
	
	private String nomeCompleto;
	
	private String telefone;
	
	private String email;
	
	private Status status;
	
}
