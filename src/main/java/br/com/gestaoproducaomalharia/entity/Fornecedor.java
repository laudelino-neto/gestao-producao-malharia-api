package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.Status;
import lombok.Data;

@Data
public class Fornecedor {
	
	private Integer id;
	
	private String nomeFantasia;
	
	private String telefone;
	
	private String email;
	
	private Status status;
	
}
