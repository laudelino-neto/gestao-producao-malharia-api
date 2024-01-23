package br.com.gestaoproducaomalharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.Cor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface CorService {

	public Cor salvar(
			@NotNull(message = "A cor é obrigatória.") 
			Cor cor);

	public Cor buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);

	public Page<Cor> listarPor(
			@NotBlank(message = "O nome da cor é obrigatória.") 
			@Size(min = 3, message = "O nome da cor deve ter no mínimo 3 caracteres.")
			String nome,	
			Pageable paginacao);

	public Cor alterar(
			@Valid @NotNull(message = "A cor é obrigatória.") 
			Cor cor);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id, 
			@NotNull(message = "O status é obrigatório.")
			Status status);

}
