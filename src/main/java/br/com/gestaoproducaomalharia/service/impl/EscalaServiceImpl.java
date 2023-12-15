package br.com.gestaoproducaomalharia.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.exception.RegistroNaoEncontradoException;
import br.com.gestaoproducaomalharia.repository.ColaboradoresRepository;
import br.com.gestaoproducaomalharia.repository.EscalasRepository;
import br.com.gestaoproducaomalharia.service.EscalaService;

@Service
public class EscalaServiceImpl implements EscalaService{
	
	@Autowired
	private EscalasRepository escalasRepository;
	
	@Autowired
	private ColaboradoresRepository colaboradoresRepository;

	@Override
	public AcertoDeEscala salvar(AcertoDeEscala acerto) {
		return null;
	}

	@Override
	public Escala salvar(Escala escala) {
		return null;
	}

	@Override
	public Escala remover(Escala escala) {
		return null;
	}

	@Override
	public List<Escala> gerarPor(Colaborador colaborador,
			LocalDate dataInicial, LocalDate dataFinal, 
			LocalTime entrada, LocalTime saida) {			
		
		Preconditions.checkArgument(dataInicial.isBefore(dataFinal), 
				"A data inicial deve ser anterior a data final");
		
		Preconditions.checkArgument(entrada.isBefore(saida), 
				"A hora de entrada não pode ser posterior a hora de saída");
		
		Colaborador colaboradorSalvo = colaboradoresRepository.buscarPor(colaborador.getId());
		
		Optional.ofNullable(colaboradorSalvo).orElseThrow(
				() -> new RegistroNaoEncontradoException("Não foi encontrado colaborador com o id informado"));
		
		long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
		
		List<Escala> escalas = new ArrayList<>();
		
		Escala escalaInicial = null;
		
		Optional<Escala> resultadoDaBusca = escalasRepository.buscarPor(colaborador, dataInicial);
		
		if (resultadoDaBusca.isPresent()) {
			escalaInicial = resultadoDaBusca.get();
		}else {
			escalaInicial = new Escala(colaborador, dataInicial, entrada, saida);
		}
		
		this.escalasRepository.save(escalaInicial);

		escalas.add(escalaInicial);
		
		//Para cada dia entre as datas será gerada uma escala
		for (long dia = 1; dia <= dias; dia++) {			
			LocalDate dataDaEscala = dataInicial.plusDays(dia);
			resultadoDaBusca = escalasRepository.buscarPor(colaborador, dataDaEscala);
			Escala escala = null;
			if (resultadoDaBusca.isPresent()) {
				escala = resultadoDaBusca.get();
				escala.setEntrada(entrada);
				escala.setSaida(saida);
			}else {				
				escala = new Escala(colaborador, dataDaEscala, entrada, saida);
			}
			this.escalasRepository.save(escala);
			escalas.add(escala);
		}

		return escalas;
		
	}

}
