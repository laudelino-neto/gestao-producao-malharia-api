package br.com.gestaoproducaomalharia.entity;

import br.com.gestaoproducaomalharia.entity.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {
	
	@Id
	@Size(max = 50, message = "O login do usuário não deve conter mais de 50 caracteres")
	@NotBlank(message = "O login do usuário é obrigatório")
	@Column(name = "login")
	private String login;
	
	@NotBlank(message = "A senha do usuário é obrigatória")
	@Column(name = "senha")
	private String senha;
	
	@Size(max = 120, message = "O nome do usuário não deve conter mais de 120 caracteres")
	@NotBlank(message = "O login do usuário é obrigatório")
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O papel do usuário é obrigatório")
	@Column(name = "papel")
	private Papel papel;

}
