package br.com.gestaoproducaomalharia.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Fornecedor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.FornecedorService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired @Qualifier("fornecedorServiceProxy")
	private FornecedorService service;
	
	@Autowired
	private MapConverter converter;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Fornecedor fornecedor) {
		Preconditions.checkArgument(!fornecedor.isPersistido(), 
				"O fornecedor não deve possuir ID na inserção.");
		Fornecedor fornecedorSalvo = service.salvar(fornecedor);
		return ResponseEntity.created(URI.create("/fornecedores/id/" + fornecedorSalvo.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Fornecedor fornecedor){
		Preconditions.checkArgument(fornecedor.isPersistido(), 
				"O fornecedor deve possuir id para a alteração.");
		Fornecedor fornecedorAlterado = service.salvar(fornecedor);
		return ResponseEntity.ok(converter.toJsonMap(fornecedorAlterado));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Fornecedor fornecedorEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(fornecedorEncontrado));
	}
	
	@Transactional
	@PatchMapping("/id/{id}/status/{status}")
	public ResponseEntity<?> atualizarStatusPor(
			@PathVariable("id")
			Integer id, 
			@PathVariable("status")
			Status status) {
		this.service.atualizarStatusPor(id, status);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<?> listarPor(
			@RequestParam("nomeFantasia")
			String nome, 
			@RequestParam("pagina")
			Optional<Integer> pagina) {
		Pageable paginacao = null;
		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);
		}
		
		Page<Fornecedor> fornecedores = service.listarPor(nome, paginacao);
		return ResponseEntity.ok(converter.toJsonList(fornecedores));
	}

}
