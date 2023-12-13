package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tamanhos")
@Entity(name = "Tamanho")
public class Tamanho implements Validavel{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(max = 4, message = "A sigla do tamanho não deve conter mais de 4 caracteres")
	@NotBlank(message = "A sigla do tamanho é obrigatória")
	@Column(name = "sigla")
	private String sigla;
	
	@Size(max = 20, message = "A descrição do tamanho não deve conter mais de 20 caracteres")
	@NotBlank(message = "A descrição do tamanho é obrigatória")
	@Column(name = "descricao")
	private String descricao;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O status do tamanho é obrigatório")
	@Column(name = "status")
	private Status status;
	
	public Tamanho() {
		this.status = Status.A;
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
