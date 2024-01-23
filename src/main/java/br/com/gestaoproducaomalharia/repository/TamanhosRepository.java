package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Tamanho;
import br.com.gestaoproducaomalharia.entity.enums.Status;

@Repository
public interface TamanhosRepository extends JpaRepository<Tamanho, Integer>{
	
	@Query(value = "SELECT t "
			+ "FROM Tamanho t "
			+ "ORDER BY t.sigla", 
			countQuery = "SELECT Count(t) "
					+ "FROM Tamanho t")
	public Page<Tamanho> listarPor(Pageable paginacao);

	@Query(value = "SELECT t "
			+ "FROM Tamanho t "
			+ "WHERE t.id = :id")
	public Tamanho buscarPor(Integer id);
	
	@Query(value = "SELECT t " 
			+ "FROM Tamanho t " 
			+ "WHERE Upper(t.sigla) = Upper(:sigla)")
	public Tamanho buscarPor(String sigla);
	
	@Modifying
	@Query(value = "UPDATE Tamanho t "
			+ "SET t.status = :status "
			+ "WHERE t.id = :id")
	public void atualizarStatusPor(Integer id, Status status);

}
