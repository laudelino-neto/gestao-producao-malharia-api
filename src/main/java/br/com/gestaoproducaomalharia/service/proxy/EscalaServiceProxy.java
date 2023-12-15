package br.com.gestaoproducaomalharia.service.proxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.service.EscalaService;

@Service
public class EscalaServiceProxy implements EscalaService {
	
	@Autowired @Qualifier("escalaServiceImpl")
	private EscalaService service;

	@Override
	public AcertoDeEscala salvar(AcertoDeEscala acerto) {
		return service.salvar(acerto);
	}

	@Override
	public AcertoDeEscala excluirPor(Integer id) {
		return service.excluirPor(id);
	}

	@Override
	public AcertoDeEscala buscarPor(Integer id) {
		return service.buscarPor(id);
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
	public List<Escala> gerarPor(Colaborador colaborador, LocalDate dataInicial, 
			LocalDate dataFinal, LocalTime entrada, LocalTime saida) {		
		return null;
	}

}
