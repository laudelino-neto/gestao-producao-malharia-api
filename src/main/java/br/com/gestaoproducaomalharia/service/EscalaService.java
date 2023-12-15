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

@Validated
public interface EscalaService {

	public AcertoDeEscala salvar(AcertoDeEscala acerto);
	
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
