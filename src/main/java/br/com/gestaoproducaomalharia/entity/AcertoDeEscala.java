package br.com.gestaoproducaomalharia.entity;

import java.time.LocalDate;

import br.com.gestaoproducaomalharia.entity.enums.TipoDeAcerto;
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

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "acertos_escalas")
@Entity(name = "AcertoDeEscala")
public class AcertoDeEscala implements Validavel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotNull(message = "O dia é obrigatório")
	@Column(name = "dia")
	private LocalDate dia;
	
	@Column(name = "data_acerto")
	private LocalDate dataDoAcerto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador")
	@NotNull(message = "O colaborador é obrigatório")
	private Colaborador colaborador;
	
	@Column(name = "tempo")
	private Integer tempo;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo é obrigatório")
	@Column(name = "tipo")
	private TipoDeAcerto tipo;
	
	public AcertoDeEscala() {
		this.dataDoAcerto = LocalDate.now();
	}
	
	@Transient
	@Override
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	public boolean isPorFalta() {
		return getTipo() == TipoDeAcerto.FALTA;
	}
	
	@Transient
	public boolean isDeHoraExtra() {
		return getTipo() == TipoDeAcerto.HORA_EXTRA;
	}
	
	@Transient
	public boolean isPorAtraso() {
		return getTipo() == TipoDeAcerto.ATRASO;
	}
	
	@Transient
	public boolean isDeCompensacao() {
		return getTipo() == TipoDeAcerto.COMPENSACAO;
	}
	
	@Transient
	public boolean isDiaPresenteOuFuturo() {
		LocalDate hoje = LocalDate.now();
		return getDia().equals(hoje) || getDia().isAfter(hoje);
	}
	
}
