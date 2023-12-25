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
import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
import br.com.gestaoproducaomalharia.exception.RegistroNaoEncontradoException;
import br.com.gestaoproducaomalharia.repository.AcertosDeEscalasRepository;
import br.com.gestaoproducaomalharia.repository.ColaboradoresRepository;
import br.com.gestaoproducaomalharia.repository.EscalasRepository;
import br.com.gestaoproducaomalharia.service.EscalaService;

@Service
public class EscalaServiceImpl implements EscalaService{
	
	@Autowired
	private EscalasRepository escalasRepository;
	
	@Autowired
	private ColaboradoresRepository colaboradoresRepository;
	
	@Autowired
	private AcertosDeEscalasRepository acertosRepository;

	@Override
	public AcertoDeEscala salvar(AcertoDeEscala acerto) {
	    
		LocalDate dia = acerto.getDia();
	    
	    Optional<Escala> escalaExistente = escalasRepository.buscarPor(
	    		acerto.getColaborador(), dia);
	    
	    Preconditions.checkArgument(escalaExistente.isPresent(), 
	    		"Não existe escala realizada para a data do acerto");	    	   
	    
		Preconditions.checkArgument(acerto.isDiaPresenteOuFuturo(), 
					"A data do acerto não pode ser posterior à data atual");
		
		if (acerto.isPorFalta()) {		
			Preconditions.checkArgument(acerto.getTempo() == null, 
					"O acerto por falta não deve possuir tempo informado");
		}
		
	    AcertoDeEscala acertoExistente = acertosRepository.buscarPor(
	    		acerto.getColaborador().getId(), dia);
	    
	    if (acertoExistente == null) {
	        return acertosRepository.save(acerto);
	    } else {
	    	acertoExistente.setTempo(acerto.getTempo());
	    	acertoExistente.setTipo(acerto.getTipo());
	    	return acertosRepository.saveAndFlush(acerto);
	    }
	    
	}

	@Override
	public AcertoDeEscala excluirAcertoPor(Integer id) {
		AcertoDeEscala acertoParaExclusao = buscarAcertoPor(id);
		this.acertosRepository.deleteById(acertoParaExclusao.getId());
		return acertoParaExclusao;
	}
	
	@Override
	public AcertoDeEscala buscarAcertoPor(Integer id) {
		AcertoDeEscala acertoEncontrado = acertosRepository.buscarPor(id);
		Preconditions.checkNotNull(acertoEncontrado, 
				"Não foi encontrado acerto para o id informado");
		return acertoEncontrado;
	}

	@Override
	public Escala salvar(Escala escala) {
		
		LocalDate dataDaEscala = escala.getData();
		
		Optional<Escala> resultadoDaBusca = escalasRepository.buscarPor(
				escala.getColaborador(), dataDaEscala);
		
		if (resultadoDaBusca.isPresent()) {
			Escala outraEscala = resultadoDaBusca.get();
			Preconditions.checkArgument(outraEscala.equals(escala), 
					"A escala possui a mesma data da escala com o id '" + outraEscala.getId() + "'");
		}
		
		this.escalasRepository.save(escala);
		
		return escalasRepository.buscarPor(escala.getId()).get();				
		
	}

	@Override
	public Escala excluirPor(Integer id) {
		Escala escalaEncontrada = buscarPor(id);
		this.escalasRepository.delete(escalaEncontrada);
		return escalaEncontrada;
	}
	
	@Override
	public Escala buscarPor(Integer id) {
		Optional<Escala> resultadoEncontrado = escalasRepository.buscarPor(id);
		return resultadoEncontrado.orElseThrow(() -> 
				new RegistroNaoEncontradoException("Não existe escala vinculada ao id '" + id + "'"));
	}
	
	@Override
	public void atualizarPor(Integer id, Confirmacao realizada) {
		Escala escalaEncontrada = buscarPor(id);
		escalaEncontrada.setRealizada(realizada);
		this.salvar(escalaEncontrada);
	}
	
	private Colaborador buscarColaboradorPor(Integer id) {

		Colaborador colaboradorSalvo = colaboradoresRepository.buscarPor(id);

		return Optional.ofNullable(colaboradorSalvo).orElseThrow(
				() -> new RegistroNaoEncontradoException(
						"Não foi encontrado colaborador com o id '" + id + "'"));

	}

	@Override
	public List<Escala> gerarPor(Colaborador colaborador,
			LocalDate dataInicial, LocalDate dataFinal, 
			LocalTime entrada, LocalTime saida) {			
		
		Preconditions.checkArgument(dataInicial.isBefore(dataFinal), 
				"A data inicial deve ser anterior a data final");
		
		Preconditions.checkArgument(entrada.isBefore(saida), 
				"A hora de entrada não pode ser posterior a hora de saída");
		
		Colaborador colaboradorSalvo = buscarColaboradorPor(colaborador.getId());
		
		long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
		
		List<Escala> escalas = new ArrayList<>();
		
		Escala escalaInicial = null;
		
		Optional<Escala> resultadoDaBusca = escalasRepository.buscarPor(colaborador, dataInicial);
		
		if (resultadoDaBusca.isPresent()) {
			escalaInicial = resultadoDaBusca.get();
		}else {
			escalaInicial = new Escala(colaboradorSalvo, dataInicial, entrada, saida);
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
				escala = new Escala(colaboradorSalvo, dataDaEscala, entrada, saida);
			}
			this.escalasRepository.save(escala);
			escalas.add(escala);
		}

		return escalas;
		
	}
	
	@Override
	public List<Escala> listarPor(Integer idDoColaborador, Integer ano, Integer mes){
		Colaborador colaboradorSalvo = buscarColaboradorPor(idDoColaborador);
		return escalasRepository.listarPor(colaboradorSalvo, ano, mes);		
	}

}
