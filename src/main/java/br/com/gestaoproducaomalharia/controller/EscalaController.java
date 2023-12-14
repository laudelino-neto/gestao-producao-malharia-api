package br.com.gestaoproducaomalharia.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.controller.converter.MapConverter;
import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.service.EscalaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
	
	@Autowired @Qualifier("escalaServiceProxy")
	private EscalaService service;
	
	@Autowired
	private MapConverter converter;
	
	@PostMapping("/acertos")
	public ResponseEntity<?> inserir(
			@RequestBody
			AcertoDeEscala acerto) {
		Preconditions.checkArgument(!acerto.isPersistido(), 
				"O acerto não deve possuir ID na inserção.");
		AcertoDeEscala acertoSalvo = service.salvar(acerto);
		return ResponseEntity.created(URI.create("/acertos/id/" + acertoSalvo.getId())).build();
	}
	
	@GetMapping("/acertos/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		AcertoDeEscala acertoEncontrado = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(acertoEncontrado));
	}
	
	@Transactional
	@DeleteMapping("/acertos/id/{id}")
	public ResponseEntity<?> excluirPor(
			@PathVariable("id")
			Integer id) {
		AcertoDeEscala acertoExcluido = service.excluirPor(id);
		return ResponseEntity.ok(converter.toJsonMap(acertoExcluido));
	}
	
}
