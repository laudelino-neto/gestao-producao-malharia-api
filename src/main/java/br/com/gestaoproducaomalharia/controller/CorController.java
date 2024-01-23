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
import br.com.gestaoproducaomalharia.entity.Cor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.CorService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cores")
class CorController {
	
	@Autowired @Qualifier("corServiceProxy")
	private CorService service;
	
	@Autowired
	private MapConverter converter;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Cor cor) {
		Preconditions.checkArgument(!cor.isPersistido(), 
				"A cor não deve possuir ID na inserção.");
		Cor corSalva = service.salvar(cor);
		return ResponseEntity.created(URI.create("/cores/id/" + corSalva.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Cor cor){
		Preconditions.checkArgument(cor.isPersistido(), 
				"A cor deve possuir id para a alteração.");
		Cor corAlterada = service.salvar(cor);
		return ResponseEntity.ok(converter.toJsonMap(corAlterada));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Cor corEncontrada = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(corEncontrada));
	}
	
	@GetMapping
	public ResponseEntity<?> listarPor(
			@RequestParam("nome")
			String nome, 
			@RequestParam("pagina")
			Optional<Integer> pagina) {
		Pageable paginacao = null;
		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);
		}
		
		Page<Cor> cores = service.listarPor(nome, paginacao);
		return ResponseEntity.ok(converter.toJsonList(cores));
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
	
}
