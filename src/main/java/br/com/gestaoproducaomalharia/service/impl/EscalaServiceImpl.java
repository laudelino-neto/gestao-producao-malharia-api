package br.com.gestaoproducaomalharia.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.repository.AcertosDeEscalasRepository;
import br.com.gestaoproducaomalharia.repository.EscalasRepository;
import br.com.gestaoproducaomalharia.service.EscalaService;

@Service
public class EscalaServiceImpl implements EscalaService {
	
	@Autowired
	private AcertosDeEscalasRepository repository;
	
	@Autowired
	private EscalasRepository escalaRepository;

	@Override
	public AcertoDeEscala salvar(AcertoDeEscala acerto) {
	    LocalDate dataAcerto = acerto.getData();
	    Escala escalaExistente = escalaRepository.buscarPor(acerto.getColaborador().getId(), 
	    		dataAcerto);
	    Preconditions.checkNotNull(escalaExistente, 
	    		"Não existe escala realizada para a data do acerto");
	    
	    LocalDate dataAtual = LocalDate.now();
			Preconditions.checkNotNull(dataAcerto.isAfter(dataAtual), 
					"A data do acerto não pode ser posterior à data atual");

	    AcertoDeEscala acertoExistente = repository.buscarPor(acerto.getColaborador().getId(), 
	    		dataAcerto);
	    if (!acertoExistente.isPersistido()) {
	        return repository.save(acerto);
	    } else {
	    	acertoExistente.setTempo(acerto.getTempo());
	    	acertoExistente.setTipo(acerto.getTipo());
	    	return repository.saveAndFlush(acerto);
	    }
	}

	@Override
	public AcertoDeEscala excluirPor(Integer id) {
		AcertoDeEscala acertoParaExclusao = buscarPor(id);
		this.repository.deleteById(acertoParaExclusao.getId());
		return acertoParaExclusao;
	}
	
	@Override
	public AcertoDeEscala buscarPor(Integer id) {
		AcertoDeEscala acertoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(acertoEncontrado, 
				"Não foi encontrado acerto para o id informado");
		return acertoEncontrado;
	}

	@Override
	public Escala salvar(Escala escala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Escala remover(Escala escala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Escala> gerarPor(LocalDate dataInicial, LocalDate dataFinal, 
			BigDecimal cargaHorariaDiaria) {
		// TODO Auto-generated method stub
		return null;
	}

}
