package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Tamanho;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.repository.TamanhosRepository;
import br.com.gestaoproducaomalharia.service.TamanhoService;

@Service
public class TamanhoServiceImpl implements TamanhoService {
	
	@Autowired
	private TamanhosRepository repository;

	@Override
	public Tamanho salvar(Tamanho tamanho) {
		Tamanho outroTamanho = repository.buscarPor(tamanho.getSigla());
		if (outroTamanho != null) {
			if (outroTamanho.isPersistido()) {
				Preconditions.checkArgument(outroTamanho.equals(tamanho), 
						"A sigla do tamanho já esta em uso.");
			}
			
		}
		Tamanho tamanhoSalvo = repository.save(tamanho);
		return tamanhoSalvo;
	}

	@Override
	public Tamanho buscarPor(Integer id) {
		Tamanho tamanhoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(tamanhoEncontrado, "Não foi encontrado tamanho para o id informado");
		Preconditions.checkArgument(tamanhoEncontrado.isAtivo(), "O tamanho está inativo.");
		return tamanhoEncontrado;
	}

	@Override
	public Tamanho alterar(Tamanho tamanho) {
		Tamanho tamanhoSalvo = repository.buscarPor(tamanho.getId());
		tamanhoSalvo.setSigla(tamanho.getSigla());
		tamanhoSalvo.setDescricao(tamanho.getDescricao());
		tamanhoSalvo.setStatus(tamanho.getStatus());
		Tamanho tamanhotualizado = repository.saveAndFlush(tamanhoSalvo);
		return buscarPor(tamanhotualizado.getId());
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Tamanho tamanhoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(tamanhoEncontrado,
				"Não existe tamanho vinculada ao id informado.");
		Preconditions.checkArgument(tamanhoEncontrado.getStatus() != status,
				"O status já está salvo para o tamanho.");
		this.repository.atualizarStatusPor(id, status);
	}

}
