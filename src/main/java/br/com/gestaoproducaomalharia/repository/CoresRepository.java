package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Cor;
import br.com.gestaoproducaomalharia.entity.enums.Status;

@Repository
public interface CoresRepository extends JpaRepository<Cor, Integer> {
	
	@Query(value = "SELECT c "
			+ "FROM Cor c "
			+ "WHERE Upper(c.nome) "
			+ "LIKE Upper(:nome) "
			+ "ORDER BY c.nome ", 
			countQuery = "SELECT Count(c) "
					+ "FROM Cor c "
					+ "WHERE Upper(c.nome) "
					+ "LIKE Upper(:nome)")
	public Page<Cor> listarPor(String nome, Pageable paginacao);
	
	@Query(value = "SELECT c " 
			+ "FROM Cor c " 
			+ "WHERE Upper(c.nome) = Upper(:nome)")
	public Cor buscarPor(String nome);
	
	@Query(value = "SELECT c " 
			+ "FROM Cor c " 
			+ "WHERE c.id = :id ")
	public Cor buscarPor(Integer id);
	
	@Modifying
	@Query(value = "UPDATE Cor c "
			+ "SET c.status = :status "
			+ "WHERE c.id = :id")
	public void atualizarStatusPor(Integer id, Status status);

}
