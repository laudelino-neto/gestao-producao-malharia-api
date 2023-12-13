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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "acertos_escalas")
@Entity(name = "AcertoDeEscala")
public class AcertoDeEscala {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@NotNull(message = "A data é obrigatória")
	@Column(name = "data_acerto")
	private LocalDate data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador")
	@NotNull(message = "O colaborador é obrigatório")
	private Colaborador colaborador;
	
	@Positive(message = "O tempo deve ser em minutos e deve ser maior que zero")
	@NotNull(message = "O tempo é obrigatório")
	@Column(name = "tempo")
	private Integer tempo;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo é obrigatório")
	@Column(name = "tipo")
	private TipoDeAcerto tipo;
	
}
