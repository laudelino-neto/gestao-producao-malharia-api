package br.com.gestaoproducaomalharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.Produto;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface ProdutoService  {
	
	public Produto salvar(
			@NotNull(message = "O produto é obrigatório.") 
			Produto produto);
	
	public Produto buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);
	
	public Produto alterar(
			@Valid
			@NotNull(message = "O produto é obrigatório")
			Produto produto);
	
	public Page<Produto> listarPor(
			@NotBlank(message = "A descrição é obrigatória.") 
			@Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres.")
			String descricao, 
			Pageable paginacao);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id, 
			@NotNull(message = "O status é obrigatório.")
			Status status);


}
