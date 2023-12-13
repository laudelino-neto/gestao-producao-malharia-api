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

import br.com.gestaoproducaomalharia.controller.converter.MapConverter;
import br.com.gestaoproducaomalharia.entity.Produto;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.ProdutoService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired @Qualifier("produtoServiceProxy")
	private ProdutoService service;
	
	@Autowired
	private MapConverter converter;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Produto produto) {
		Preconditions.checkArgument(!produto.isPersistido(), 
				"O produto não deve possuir ID na inserção.");
		Produto produtoSalvo = service.salvar(produto);
		return ResponseEntity.created(URI.create("/produtos/id/" + produtoSalvo.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Produto produto){
		Preconditions.checkArgument(produto.isPersistido(), 
				"O produto deve possuir id para a alteração.");
		Produto produtoAlterado = service.salvar(produto);
		return ResponseEntity.ok(converter.toJsonMap(produtoAlterado));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Produto produtoEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(produtoEncontrado));
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
			@RequestParam("descricao")
			String descricao,
			@RequestParam("pagina")
			Optional<Integer> pagina) {
		Pageable paginacao = null;
		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);
		}
		
		Page<Produto> produtos = service.listarPor(descricao, paginacao);
		return ResponseEntity.ok(converter.toJsonList(produtos));
	}
	
}
