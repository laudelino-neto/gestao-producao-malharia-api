package br.com.gestaoproducaomalharia.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gestaoproducaomalharia.entity.Produto;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.service.ProdutoService;

@Service
public class ProdutoServiceProxy implements ProdutoService {
	
	@Autowired @Qualifier("produtoServiceImpl")
	private ProdutoService service;

	@Override
	public Produto salvar(Produto produto) {
		return service.salvar(produto);
	}

	@Override
	public Produto buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Produto alterar(Produto produto) {
		return service.alterar(produto);
	}

	@Override
	public Page<Produto> listarPor(String descricao, Pageable paginacao) {
		return service.listarPor("%" + descricao + "%", paginacao);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		this.service.atualizarStatusPor(id, status);
	}

}
