package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "lancamentos_producao")
@Entity(name = "LancamentoDeProducao")
public class LancamentoDeProducao {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador")
	@NotNull(message = "O colaborador é obrigatório")
	private Colaborador colaborador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ordem")
	@NotNull(message = "A ordem de produção do lançamento é obrigatória")
	private OrdemDeProducao ordem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto")
	@NotNull(message = "O produto do lançamento é obrigatório")
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tamanho")
	@NotNull(message = "O tamanho do produto produzido é obrigatório")
	private Tamanho tamanho;
	
	@Positive(message = "A quantidade do item é obrigatória")
	@Column(name = "qtde")
	private Integer quantidade;	
	
	@NotNull(message = "A quantidade do lançamento é obrigatória")
	@Positive(message = "A quantidade do lançamento deve ser positiva")
	@Column(name = "data_movto")
	private LocalDateTime dataDeMovto;
	
}
