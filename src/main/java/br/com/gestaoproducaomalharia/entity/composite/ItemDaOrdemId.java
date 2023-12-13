package br.com.gestaoproducaomalharia.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemDaOrdemId {

	@NotNull(message = "O id da ordem é obrigatório")
	@Positive(message = "O id da ordem é obrigatório")
	@Column(name = "id_ordem")
	private Integer idDaOrdem;
	
	@NotNull(message = "O id do produto é obrigatório")
	@Positive(message = "O id do produto é obrigatório")
	@Column(name = "id_produto")
	private Integer idDoProduto;
	
	@NotNull(message = "O id do tamanho é obrigatório")
	@Positive(message = "O id do tamanho é obrigatório")
	@Column(name = "id_tamanho")
	private Integer idDoTamanho;
	
}
