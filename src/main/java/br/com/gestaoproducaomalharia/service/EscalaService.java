package br.com.gestaoproducaomalharia.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Validated
public interface EscalaService {

	public AcertoDeEscala salvar(
			@Valid
			@NotNull(message = "O acerto é obrigatório") 
			AcertoDeEscala acerto);
	
	public AcertoDeEscala excluirAcertoPor(
			@NotNull(message = "O id para a exclusão é obrigatório") 
			@Positive(message = "O id para a exclusão deve ser positivo")
			Integer id);
	
	public AcertoDeEscala buscarAcertoPor(
			@NotNull(message = "O id é obrigatório") 
			@Positive(message = "O id deve ser positivo")
			Integer id);
	
	public Escala salvar(
			@Valid
			@NotNull(message = "A escala é obrigatória")
			Escala escala);
	
	public Escala excluirPor(
			@NotNull(message = "O id é obrigatório") 
			@Positive(message = "O id deve ser positivo")
			Integer id);
	
	public Escala buscarPor(
			@NotNull(message = "O id é obrigatório") 
			@Positive(message = "O id deve ser positivo")
			Integer id);
	
	
	public void atualizarPor(
			@NotNull(message = "O id é obrigatório") 
			@Positive(message = "O id deve ser positivo")
			Integer id,
			@NotNull(message = "O indicados 'realizada' é obrigatório")
			Confirmacao realizada);
	
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
	
	public List<Escala> listarPor(
			@NotNull(message = "O colaborador é obrigatório")
			@Positive(message = "O id do colaborador deve ser positivo")
			Integer idDoColaborador,
			@NotNull(message = "O ano é obrigatório") 
			@Positive(message = "O ano deve ser positivo")
			Integer ano,
			@NotNull(message = "O mês das escalas é obrigatório")			
			@Min(value = 1, message = "O mês deve ser maior que 1")
			@Max(value = 12, message = "O mês deve ser menor 12")
			Integer mes);
	
	
}
