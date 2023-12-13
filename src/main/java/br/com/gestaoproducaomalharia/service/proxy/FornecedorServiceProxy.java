package br.com.gestaoproducaomalharia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.Fornecedor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.FornecedorService;

@Service
public class FornecedorServiceProxy implements FornecedorService {
	
	@Autowired @Qualifier("fornecedorServiceImpl")
	private FornecedorService service;

	@Override
	public Fornecedor salvar(Fornecedor fornecedor) {
		return service.salvar(fornecedor);
	}

	@Override
	public Fornecedor buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Page<Fornecedor> listarPor(String nomeFantasia, Pageable paginacao) {
		return service.listarPor(nomeFantasia, paginacao);
	}

	@Override
	public Fornecedor alterar(Fornecedor fornecedor) {
		return service.alterar(fornecedor);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);
	}

}
