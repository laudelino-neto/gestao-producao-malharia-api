package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Cor;
import br.com.gestaoproducaomalharia.entity.enums.Status;
import br.com.gestaoproducaomalharia.repository.CoresRepository;
import br.com.gestaoproducaomalharia.service.CorService;

@Service
public class CorServiceImpl implements CorService {
	
	@Autowired
	private CoresRepository repository;

	@Override
	public Cor salvar(Cor cor) {
		Cor outraCor = repository.buscarPor(cor.getNome());
		if (outraCor != null) {
			if (outraCor.isPersistido()) {
				Preconditions.checkArgument(outraCor.equals(cor), 
						"O nome da cor já esta em uso.");
			}
			
		}
		Cor corSalva = repository.save(cor);
		return corSalva;
	}

	@Override
	public Cor buscarPor(Integer id) {
		Cor corEncontrada = repository.buscarPor(id);
		Preconditions.checkNotNull(corEncontrada, 
				"Não foi encontrada cor para o id informado");
		return corEncontrada;
	}

	@Override
	public Page<Cor> listarPor(String nome, Pageable paginacao) {
		return repository.listarPor("%" + nome + "%", paginacao);
	}

	@Override
	public Cor alterar(Cor cor) {
		Cor corSalva = repository.buscarPor(cor.getId());
		corSalva.setNome(cor.getNome());
		Cor corAtualizada = repository.saveAndFlush(corSalva);
		return buscarPor(corAtualizada.getId());
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Cor corEncontrada = repository.buscarPor(id);
		Preconditions.checkNotNull(corEncontrada,
				"Não existe cor vinculada ao id informado.");
		Preconditions.checkArgument(corEncontrada.getStatus() != status,
				"O status já está salvo para a cor.");
		this.repository.atualizarStatusPor(id, status);
	}

}
