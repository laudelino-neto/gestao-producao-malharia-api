package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Produto;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.repository.ProdutosRepository;
import br.com.gestaoproducaomalharia.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutosRepository repository;

	@Override
	public Produto salvar(Produto produto) {
		Produto outroProduto = repository.buscarPor(produto.getDescricao());
		if (outroProduto!= null) {
			if (outroProduto.isPersistido()) {
				Preconditions.checkArgument(outroProduto.equals(produto), 
						"A descrição do produto já esta em uso.");
			}
			
		}
		Produto produtoSalvo = repository.save(produto);
		return produtoSalvo;
	}

	@Override
	public Produto buscarPor(Integer id) {
		Produto produtoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(produtoEncontrado, "Não foi encontrado produto para o id informado");
		Preconditions.checkArgument(produtoEncontrado .isAtivo(), "O produto está inativo.");
		return produtoEncontrado ;
	}

	@Override
	public Produto alterar(Produto produto) {
		Produto produtoSalvo = repository.buscarPor(produto.getId());
		produtoSalvo.setDescricao(produto.getDescricao());
		produtoSalvo.setStatus(produto.getStatus());
		Produto produtoAtualizado = repository.saveAndFlush(produtoSalvo);
		return buscarPor(produtoAtualizado.getId());
	}

	@Override
	public Page<Produto> listarPor(String descricao, Pageable paginacao) {
		return repository.listarPor("%" + descricao + "%", paginacao);
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Produto produtoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(produtoEncontrado,
				"Não existe produto vinculada ao id informado.");
		Preconditions.checkArgument(produtoEncontrado.getStatus() != status,
				"O status já está salvo para o produto.");
		this.repository.atualizarStatusPor(id, status);
	}

}
