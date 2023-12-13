package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Fornecedor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.repository.FornecedoresRepository;
import br.com.gestaoproducaomalharia.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {
	
	@Autowired
	private FornecedoresRepository repository;

	@Override
	public Fornecedor salvar(Fornecedor fornecedor) {
		Fornecedor outroFornecedor = repository.buscarPor(fornecedor.getNomeFantasia());
		if (outroFornecedor != null) {
			if (outroFornecedor.isPersistido()) {
				Preconditions.checkArgument(outroFornecedor.equals(fornecedor), 
						"O nome fantasia do fornecedor já esta em uso.");
			}
			
		}
		Fornecedor fornecedorSalvo = repository.save(fornecedor);
		return fornecedorSalvo;
	}

	@Override
	public Fornecedor buscarPor(Integer id) {
		Fornecedor fornecedorEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(fornecedorEncontrado, "Não foi encontrado fornecedor para o id informado");
		Preconditions.checkArgument(fornecedorEncontrado.isAtivo(), "O fornecedor está inativo.");
		return fornecedorEncontrado;
	}

	@Override
	public Page<Fornecedor> listarPor(String nomeFantasia, Pageable paginacao) {
		return repository.listarPor("%" + nomeFantasia + "%", paginacao);
	}

	@Override
	public Fornecedor alterar(Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = repository.buscarPor(fornecedor.getId());
		fornecedorSalvo.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorSalvo.setEmail(fornecedor.getEmail());
		fornecedorSalvo.setTelefone(fornecedor.getTelefone());
		fornecedorSalvo.setStatus(fornecedor.getStatus());
		Fornecedor fornecedorAtualizado = repository.saveAndFlush(fornecedorSalvo);
		return buscarPor(fornecedorAtualizado.getId());
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Fornecedor fornecedorEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(fornecedorEncontrado,
				"Não existe fornecedor vinculada ao id informado.");
		Preconditions.checkArgument(fornecedorEncontrado.getStatus() != status,
				"O status já está salvo para o fornecedor.");
		this.repository.atualizarStatusPor(id, status);
	}

}
