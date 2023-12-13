package br.com.gestaoproducaomalharia.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Escala;

@Validated
public interface EscalaService {

	public AcertoDeEscala salvar(AcertoDeEscala acerto);
	
	public Escala salvar(Escala escala);
	
	public Escala remover(Escala escala);
	
	public List<Escala> gerarPor(LocalDate dataInicial, 
			LocalDate dataFinal, BigDecimal cargaHorariaDiaria);
}
