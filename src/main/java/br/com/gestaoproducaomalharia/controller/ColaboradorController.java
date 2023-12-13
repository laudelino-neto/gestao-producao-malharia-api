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
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.ColaboradorService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired @Qualifier("colaboradorServiceProxy")
	private ColaboradorService service;
	
	@Autowired
	private MapConverter converter;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Colaborador colaborador) {
		Preconditions.checkArgument(!colaborador.isPersistido(), 
				"O colaborador não deve possuir ID na inserção.");
		Colaborador colaboradorSalvo = service.salvar(colaborador);
		return ResponseEntity.created(URI.create("/colaboradores/id/" + colaboradorSalvo.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Colaborador colaborador){
		Preconditions.checkArgument(colaborador.isPersistido(), 
				"O colaborador deve possuir id para a alteração.");
		Colaborador colaboradorAlterado = service.salvar(colaborador);
		return ResponseEntity.ok(converter.toJsonMap(colaboradorAlterado));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		Colaborador colaboradorEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(colaboradorEncontrado));
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
		
		Page<Colaborador> colaboradores = service.listarPor(nome, paginacao);
		return ResponseEntity.ok(converter.toJsonList(colaboradores));
	}

}
