package br.com.gestaoproducaomalharia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.ColaboradorService;

@Service
public class ColaboradorServiceProxy  implements ColaboradorService {
	
	@Autowired @Qualifier("colaboradorServiceImpl")
	private ColaboradorService service;

	@Override
	public Colaborador salvar(Colaborador colaborador) {
		return service.salvar(colaborador);
	}

	@Override
	public Colaborador buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Page<Colaborador> listarPor(String nomeCompleto, Pageable paginacao) {
		return service.listarPor(nomeCompleto, paginacao);
	}

	@Override
	public Colaborador alterar(Colaborador colaborador) {
		return service.alterar(colaborador);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);		
	}

}
