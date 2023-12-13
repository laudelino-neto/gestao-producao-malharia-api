package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ordens_producao")
@Entity(name = "OrdemDeProducao")
public class OrdemDeProducao implements Validavel{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fornecedor")
	@NotNull(message = "O fornecedor é obrigatório")	
	private Fornecedor fornecedor;
	
	@NotNull(message = "A data do pedido é obrigatória")
	@Column(name = "data_pedido")
	private LocalDate dataDoPedido;
	
	@Column(name = "data_entrega")
	private LocalDate dataDeEntrega;
	
	@NotNull(message = "O total de itens é obrigatório")
	@Positive(message = "O total de itens deve ser maior que zero")
	@Column(name = "total_itens")
	private Integer totalDeItens;
	
	@Positive(message = "O total produzido deve ser maior que zero")
	@Column(name = "total_produzido")
	private Integer totalProduzido;
	
	@OneToMany(mappedBy = "ordem", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemDeProducao> itens;

	@OneToMany(mappedBy = "ordem", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LancamentoDeProducao> lancamentos;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O status do tamanho é obrigatório")
	@Column(name = "status")
	private Status status;
	
	public OrdemDeProducao() {
		this.status = Status.A;
		this.itens = new ArrayList<>();
	}
	
	@Transient
	@Override
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}

	@Transient
	@Override
	public boolean isAtivo() {
		return getStatus() == Status.A;
	}
	
}
