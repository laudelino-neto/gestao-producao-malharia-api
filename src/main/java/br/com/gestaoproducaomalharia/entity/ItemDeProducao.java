package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.composite.ItemDaOrdemId;
import lombok.Data;

@Data
public class ItemDeProducao {
	
	private ItemDaOrdemId id;

	private OrdemDeProducao ordem;
	
	private Produto produto;
	
	private Tamanho tamanho;
	
	private Integer quantidade;	
	
}
