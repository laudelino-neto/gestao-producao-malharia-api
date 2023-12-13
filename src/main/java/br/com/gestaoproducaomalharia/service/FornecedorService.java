package br.com.gestaoproducaomalharia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.Fornecedor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface FornecedorService {
	
	public Fornecedor salvar(
			@NotNull(message = "O Fornecedor é obrigatório.") 
			Fornecedor fornecedor);
	
	public Fornecedor buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);
	
	public Page<Fornecedor> listarPor(	
			@NotBlank(message = "O nome fantasia é obrigatório.") 
			@Size(min = 3, message = "O nome fantasia deve ter no mínimo 3 caracteres.")
			String nomeFantasia, 
			Pageable paginacao);
	
	public Fornecedor alterar(
			@Valid
			@NotNull(message = "O Fornecedor é obrigatório")
			Fornecedor fornecedor);
	
	public void atualizarStatusPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id, 
			@NotNull(message = "O status é obrigatório.")
			Status status);

}
