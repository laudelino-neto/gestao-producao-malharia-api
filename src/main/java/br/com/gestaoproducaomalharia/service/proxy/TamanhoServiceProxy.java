package br.com.gestaoproducaomalharia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.Tamanho;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.TamanhoService;

@Service
public class TamanhoServiceProxy implements TamanhoService {
	
	@Autowired @Qualifier("tamanhoServiceImpl")
	private TamanhoService service;

	@Override
	public Tamanho salvar(Tamanho tamanho) {
		return service.salvar(tamanho);
	}

	@Override
	public Tamanho buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Tamanho alterar(Tamanho tamanho) {
		return service.alterar(tamanho);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);
	}
	
	@Override
	public Page<Tamanho> listarPor(Pageable paginacao) {
		return service.listarPor(paginacao);
	}

}
