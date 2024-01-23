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
@Table(name = "cores_itens")
@Entity(name = "Cor")
public class Cor implements Validavel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "nome")
	@Size(max = 100, min = 3, 
		message = "O nome da cor deve conter entre 3 a 100 caracteres")
	@NotBlank(message = "O nome da cor é obrigatória.")
	private String nome;
	
	@NotNull(message = "O status da cor é obrigatória.")
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	public Cor() {
		this.status = Status.A;
	}

	@Transient @Override 
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	@Override
	public boolean isAtivo() {
		return getStatus() == Status.A;
	}	

}
