package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Produto;
import br.com.gestaoproducaomalharia.entity.enums.Status;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
	
	@Query(value = "SELECT p FROM Produto p WHERE Upper(p.descricao) LIKE Upper(:descricao) ORDER BY p.descricao", 
			countQuery = "SELECT Count(p) FROM Produto p WHERE Upper(p.descricao) LIKE Upper(:descricao)")
	public Page<Produto> listarPor(String descricao, Pageable paginacao);
	
	@Query(value = "SELECT p FROM Produto p WHERE p.id = :id")
	public Produto buscarPor(Integer id);
	
	@Query(value = "SELECT p " 
			+ "FROM Produto p " 
			+ "WHERE Upper(p.descricao) = Upper(:descricao)")
	public Produto buscarPor(String descricao);
	
	@Modifying
	@Query(value = "UPDATE Produto p SET p.status = :status WHERE p.id = :id")
	public void atualizarStatusPor(Integer id, Status status);

}
