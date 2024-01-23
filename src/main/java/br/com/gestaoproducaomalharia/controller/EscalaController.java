package br.com.gestaoproducaomalharia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.controller.converter.EscalaConverter;
import br.com.gestaoproducaomalharia.controller.converter.MapConverter;
import br.com.gestaoproducaomalharia.dto.ParametrosDeGeracao;
import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
import br.com.gestaoproducaomalharia.service.EscalaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
	
	@Autowired @Qualifier("escalaServiceProxy")
	private EscalaService service;
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	private EscalaConverter escalaConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> inserir(Escala novaEscala){
		Preconditions.checkArgument(!novaEscala.isPersistido(), 
				"A escala não deve possuir id informado");
		Escala escalaSalva = service.salvar(novaEscala);
		return ResponseEntity.created(URI.create("/id/" + escalaSalva.getId())).build();
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<?> alterar(Escala escalaSalva){
		Preconditions.checkArgument(escalaSalva.isPersistido(), 
				"O id da escala é obrigatório");
		Escala escalaAtualizada = service.salvar(escalaSalva);
		return ResponseEntity.ok(converter.toJsonMap(escalaAtualizada));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id")
			Integer id){
		Escala escalaEncontrada = service.buscarPor(id);
		return ResponseEntity.ok(converter.toJsonMap(escalaEncontrada));
	}
	
	@Transactional
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(
			@PathVariable("id")
			Integer id){
		Escala escalaExcluida = service.excluirPor(id);
		return ResponseEntity.ok(converter.toJsonMap(escalaExcluida));		
	}
	
	@PostMapping("/acerto")
	public ResponseEntity<?> inserir(
			@RequestBody
			AcertoDeEscala acerto) {
		Preconditions.checkArgument(!acerto.isPersistido(), 
				"O acerto não deve possuir ID na inserção.");
		AcertoDeEscala acertoSalvo = service.salvar(acerto);
		return ResponseEntity.created(URI.create("/acertos/id/" + acertoSalvo.getId())).build();
	}	
	
	@GetMapping("/acerto/id/{id}")
	public ResponseEntity<?> buscarAcertoPor(@PathVariable("id") Integer id) {
		AcertoDeEscala acertoEncontrado = service.buscarAcertoPor(id);
		return ResponseEntity.ok(converter.toJsonMap(acertoEncontrado));
	}
	
	@Transactional
	@DeleteMapping("/acerto/id/{id}")
	public ResponseEntity<?> excluirAcertoPor(
			@PathVariable("id")
			Integer id) {
		AcertoDeEscala acertoExcluido = service.excluirAcertoPor(id);
		return ResponseEntity.ok(converter.toJsonMap(acertoExcluido));
	}
	
	@Transactional
	@PatchMapping("/id/{id}/realizada")
	public ResponseEntity<?> marcarRealizacaoPor(
			@PathVariable("id")
			Integer id){
		this.service.atualizarPor(id, Confirmacao.S);
		return ResponseEntity.created(URI.create("/acertos/id/" + id)).build();
	}
	
	@Transactional
	@PatchMapping("/id/{id}/nao-realizada")
	public ResponseEntity<?> marcarNaoRealizacaoPor(
			@PathVariable("id")
			Integer id){
		this.service.atualizarPor(id, Confirmacao.N);
		return ResponseEntity.created(URI.create("/acertos/id/" + id)).build();
	}
	
	@Transactional
	@PostMapping("/gerar-periodo")
	public ResponseEntity<?> gerarPor(
			@RequestBody
			ParametrosDeGeracao parametros){
		
		List<Escala> escalasGeradas = service.gerarPor(parametros.getColaborador(), 
				parametros.getDataInicial(), parametros.getDataFinal(), 
				parametros.getEntrada(), parametros.getSaida());
		
		return ResponseEntity.ok(escalaConverter.toMap(escalasGeradas));
		
	}
	
}
