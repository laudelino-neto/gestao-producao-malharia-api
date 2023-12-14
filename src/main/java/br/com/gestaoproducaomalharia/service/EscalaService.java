package br.com.gestaoproducaomalharia.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Escala;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface EscalaService {

	public AcertoDeEscala salvar(
			@NotNull(message = "O acerto é obrigatório.") 
			AcertoDeEscala acerto);
	
	public AcertoDeEscala excluirPor(
			@NotNull(message = "O id para a exclusão é obrigatório.") 
			@Positive(message = "O id para a exclusão deve ser positivo.")
			Integer id);
	
	public AcertoDeEscala buscarPor(
			@NotNull(message = "O id é obrigatório.") 
			@Positive(message = "O id deve ser positivo.")
			Integer id);
	
	public Escala salvar(Escala escala);
	
	public Escala remover(Escala escala);
	
	public List<Escala> gerarPor(LocalDate dataInicial, 
			LocalDate dataFinal, BigDecimal cargaHorariaDiaria);
}
