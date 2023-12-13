package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.composite.ItemDaOrdemId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "itens_ordens")
@Entity(name = "ItemDeProducao")
public class ItemDeProducao {
	
	@EmbeddedId
	@NotNull(message = "O id do item é obrigatório")
	private ItemDaOrdemId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idDaOrdem")
	@JoinColumn(name = "id_ordem")
	@NotNull(message = "A ordem do item é obrigatória")
	private OrdemDeProducao ordem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idDoProduto")
	@JoinColumn(name = "id_produto")
	@NotNull(message = "O produto do item é obrigatório")
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idDoTamanho")
	@JoinColumn(name = "id_tamanho")
	@NotNull(message = "O tamanho do item é obrigatório")
	private Tamanho tamanho;
	
	@NotNull(message = "A quantidade do item é obrigatória")
	@Positive(message = "A quantidade do item deve ser positiva")
	@Column(name = "qtde")
	private Integer quantidade;	
	
}
