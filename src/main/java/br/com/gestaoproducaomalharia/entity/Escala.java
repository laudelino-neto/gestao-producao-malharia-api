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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "escalas")
@Entity(name = "Escala")
@ToString
public class Escala implements Validavel{
	
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
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O indicador 'Justificada' é obrigatório")
	@Column(name = "fl_justificada")
	private Confirmacao justificada;
	
	@Column(name = "justificativa")
	private String justificativa;
	
	@Transient
	private AcertoDeEscala acerto;
	
	public Escala() {
		this.realizada = Confirmacao.S;
		this.justificada = Confirmacao.N;
	}

	public Escala(Colaborador colaborador, LocalDate data, 
			LocalTime entrada, LocalTime saida) {
		this();
		this.colaborador = colaborador;
		this.data = data;
		this.entrada = entrada;
		this.saida = saida;
	}
	
	@Transient
	@Override
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	public boolean isJustificada() {
		return getJustificada() == Confirmacao.S; 
	}
	
	@Transient
	public boolean isJaRealizada() {		
		//A escala só é realizada de fato quando ela está confirmada e a data é posterior a data atual
		return getData().isBefore(LocalDate.now()) && getRealizada() == Confirmacao.S; 
	}
		
	@Transient
	public boolean isParaRealizar() {
		return !isJaRealizada();
	}
	
	@Transient
	public boolean isAcertoRealizado() {
		return getAcerto() != null;
	}
	
	@Transient
	public boolean isFaltante() {
		return isAcertoRealizado() && getAcerto().isPorFalta();
	}	
	
}
