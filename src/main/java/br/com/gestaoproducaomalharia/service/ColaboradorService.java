package br.com.gestaoproducaomalharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface ColaboradorService {
	
	public Colaborador salvar(
			@NotNull(message = "O colaborador é obrigatório.") 
			Colaborador colaborador);
	
	public Colaborador buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);
	
	public Page<Colaborador> listarPor(	
			@NotBlank(message = "O nome completo é obrigatório.") 
			@Size(min = 3, message = "O nome completo deve ter no mínimo 3 caracteres.")
			String nomeCompleto, 
			Pageable paginacao);
	
	public Colaborador alterar(
			@Valid
			@NotNull(message = "O colaborador é obrigatório")
			Colaborador colaborador);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id, 
			@NotNull(message = "O status é obrigatório.")
			Status status);

}
