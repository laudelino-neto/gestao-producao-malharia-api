package br.com.gestaoproducaomalharia.service.proxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.dto.RelatorioDeEscalas;
import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;
import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;
import br.com.gestaoproducaomalharia.entity.enums.Confirmacao;
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
	public AcertoDeEscala excluirAcertoPor(Integer id) {
		return service.excluirAcertoPor(id);
	}

	@Override
	public AcertoDeEscala buscarAcertoPor(Integer id) {
		return service.buscarAcertoPor(id);
	}

	@Override
	public Escala salvar(Escala escala) {	
		return service.salvar(escala);
	}

	@Override
	public Escala excluirPor(Integer id) {
		return service.excluirPor(id);
	}
	
	@Override
	public Escala buscarPor(Integer id) {
		return service.buscarPor(id);
	}
	
	@Override
	public void atualizarPor(Integer id, Confirmacao realizada) {
		this.service.atualizarPor(id, realizada);
	}

	@Override
	public List<Escala> gerarPor(Colaborador colaborador, LocalDate dataInicial, 
			LocalDate dataFinal, LocalTime entrada, LocalTime saida) {		
		return service.gerarPor(colaborador, dataInicial, dataFinal, entrada, saida);
	}
	
	@Override
	public List<Escala> listarPor(Integer idDoColaborador, Integer ano, Integer mes){
		return service.listarPor(idDoColaborador, ano, mes);
	}
	
	@Override
	public RelatorioDeEscalas gerarPor(Integer idDoColaborador, Integer ano, Integer mes){
		return service.gerarPor(idDoColaborador, ano, mes);
	}

}
