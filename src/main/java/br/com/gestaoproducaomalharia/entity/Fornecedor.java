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
@Table(name = "fornecedores")
@Entity(name = "Fornecedor")
public class Fornecedor implements Validavel{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(max = 150, message = "O nome fantasia do fornecedor não deve conter mais de 150 caracteres")
	@NotBlank(message = "O nome fantasia do fornecedor é obrigatório")
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	
	@Size(max = 15, message = "")
	@Column(name = "telefone")	
	private String telefone;
	
	@Size(max = 100, message = "")
	@Column(name = "email")
	private String email;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O status do fornecedor é obrigatório")
	@Column(name = "status")
	private Status status;
	
	public Fornecedor() {
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
