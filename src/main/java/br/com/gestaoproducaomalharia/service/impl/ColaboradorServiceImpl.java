package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.repository.ColaboradoresRepository;
import br.com.gestaoproducaomalharia.service.ColaboradorService;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {
	
	@Autowired
	private ColaboradoresRepository repository;

	@Override
	public Colaborador salvar(Colaborador colaborador) {
		Colaborador outroColaborador = repository.buscarPor(colaborador.getNomeCompleto());
		if (outroColaborador != null) {
			if (outroColaborador.isPersistido()) {
				Preconditions.checkArgument(outroColaborador.equals(colaborador), 
						"O nome do colaborador já esta em uso.");
			}
			
		}
		Colaborador colaboradorSalvo = repository.save(colaborador);
		return colaboradorSalvo;
	}

	@Override
	public Colaborador buscarPor(Integer id) {
		Colaborador colaboradorEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(colaboradorEncontrado, "Não foi encontrado colaborador para o id informado");
		Preconditions.checkArgument(colaboradorEncontrado.isAtivo(), "O colaborador está inativo.");
		return colaboradorEncontrado;
	}

	@Override
	public Page<Colaborador> listarPor(String nomeCompleto,	Pageable paginacao) {
		return repository.listarPor("%" + nomeCompleto + "%", paginacao);
	}

	@Override
	public Colaborador alterar(Colaborador colaborador) {
		Colaborador colaboradorSalvo = repository.buscarPor(colaborador.getId());
		colaboradorSalvo.setNomeCompleto(colaborador.getNomeCompleto());
		colaboradorSalvo.setEmail(colaborador.getEmail());
		colaboradorSalvo.setTelefone(colaborador.getTelefone());
		colaboradorSalvo.setStatus(colaborador.getStatus());
		Colaborador colaboradorAtualizado = repository.saveAndFlush(colaboradorSalvo);
		return buscarPor(colaboradorAtualizado.getId());
	}
	
	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Colaborador colaboradorEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(colaboradorEncontrado,
				"Não existe colaborador vinculada ao id informado.");
		Preconditions.checkArgument(colaboradorEncontrado.getStatus() != status,
				"O status já está salvo para o colaborador.");
		this.repository.atualizarStatusPor(id, status);
	}
	
}
