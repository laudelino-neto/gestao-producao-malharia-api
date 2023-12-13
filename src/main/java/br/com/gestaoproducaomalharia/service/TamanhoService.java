package br.com.gestaoproducaomalharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.Tamanho;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface TamanhoService {
	
	public Tamanho salvar(
			@NotNull(message = "O tamanho é obrigatório.") 
			Tamanho tamanho);
	
	public Tamanho buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);
	
	public Tamanho alterar(
			@Valid
			@NotNull(message = "O tamanho é obrigatório")
			Tamanho tamanho);
	
	public Page<Tamanho> listarPor(
			Pageable paginacao);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id, 
			@NotNull(message = "O status é obrigatório.")
			Status status);

}
