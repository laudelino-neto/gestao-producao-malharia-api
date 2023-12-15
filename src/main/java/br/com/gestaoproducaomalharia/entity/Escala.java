package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "escalas")
@Entity(name = "Escala")
@ToString
public class Escala {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador")
	@NotNull(message = "O colaborador é obrigatório")
	private Colaborador colaborador;
	
	@NotNull(message = "A data é obrigatória")
	@Column(name = "data")
	private LocalDate data;
	
	@NotNull(message = "A entrada é obrigatória")
	@Column(name = "entrada")
	private LocalTime entrada;
	
	@NotNull(message = "A saída é obrigatória")
	@Column(name = "saida")
	private LocalTime saida;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O indicador 'Realizado' é obrigatório")
	@Column(name = "fl_realizada")
	private Confirmacao realizada;
	
	public Escala() {
		this.realizada = Confirmacao.S;
	}

	public Escala(Colaborador colaborador, LocalDate data, 
			LocalTime entrada, LocalTime saida) {
		this();
		this.colaborador = colaborador;
		this.data = data;
		this.entrada = entrada;
		this.saida = saida;
	}	
	
}
