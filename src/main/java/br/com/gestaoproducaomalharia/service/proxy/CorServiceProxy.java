package br.com.gestaoproducaomalharia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.Cor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.CorService;

@Service
public class CorServiceProxy implements CorService {
	
	@Autowired @Qualifier("corServiceImpl")
	private CorService service;

	@Override
	public Cor salvar(Cor cor) {
		return service.salvar(cor);
	}

	@Override
	public Cor buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Page<Cor> listarPor(String nome,	Pageable paginacao) {
		return service.listarPor(nome, paginacao);
	}

	@Override
	public Cor alterar(Cor cor) {
		return service.alterar(cor);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);		
	}

}
