package br.com.gestaoproducaomalharia.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import jakarta.validation.constraints.FutureOrPresent;
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
	
	public List<Escala> gerarPor(
			@NotNull(message = "O colaborador é obrigatório")
			Colaborador colaborador,
			@NotNull(message = "A data inicial é obrigatória")
			@FutureOrPresent(message = "A data inicial não pode ser anterior a data atual")
			LocalDate dataInicial, 
			@NotNull(message = "A data final é obrigatória")
			@FutureOrPresent(message = "A data final não pode ser anterior a data atual")
			LocalDate dataFinal,
			@NotNull(message = "A entrada é obrigatória")
			LocalTime entrada,
			@NotNull(message = "A saida é obrigatória")
			LocalTime saida);
}
