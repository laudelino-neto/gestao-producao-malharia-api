package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Fornecedor;
import br.com.gestaoproducaomalharia.entity.enums.Status;

@Repository
public interface FornecedoresRepository extends JpaRepository<Fornecedor, Integer>{
	
	@Query(value = "SELECT f "
			+ "FROM Fornecedor f "
			+ "WHERE Upper(f.nomeFantasia) LIKE Upper(:nomeFantasia) "
			+ "ORDER BY f.nomeFantasia ", 
			countQuery = "SELECT Count(f) "
					+ "FROM Fornecedor f "
					+ "WHERE Upper(f.nomeFantasia) LIKE Upper(:nomeFantasia)")
	public Page<Fornecedor> listarPor(String nomeFantasia, Pageable paginacao);
	
	@Query(value = "SELECT f "
			+ "FROM Fornecedor f "
			+ "WHERE f.id = :id")
	public Fornecedor buscarPor(Integer id);
	
	@Query(value = "SELECT f " 
			+ "FROM Fornecedor f " 
			+ "WHERE Upper(f.nomeFantasia) = Upper(:nomeFantasia)")
	public Fornecedor buscarPor(String nomeFantasia);
	
	@Modifying
	@Query(value = "UPDATE Fornecedor f "
			+ "SET f.status = :status "
			+ "WHERE f.id = :id")
	public void atualizarStatusPor(Integer id, Status status);

}
