package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.enums.Status;

@Repository
public interface ColaboradoresRepository extends JpaRepository<Colaborador, Integer> {
	
	@Query(value = 
			"SELECT c "
			+ "FROM Colaborador c "
			+ "WHERE Upper(c.nomeCompleto) LIKE Upper(:nomeCompleto) "
			+ "ORDER BY c.nomeCompleto ", 
			countQuery = 
				"SELECT Count(c) "
				+ "FROM Colaborador c "
				+ "WHERE Upper(c.nomeCompleto) LIKE Upper(:nomeCompleto) ")
	public Page<Colaborador> listarPor(String nomeCompleto, Pageable paginacao);
	
	@Query(value = 
			"SELECT c "
			+ "FROM Colaborador c "
			+ "WHERE c.id = :id")
	public Colaborador buscarPor(Integer id);
	
	@Query(value = 
			"SELECT c " 
			+ "FROM Colaborador c " 
			+ "WHERE Upper(c.nomeCompleto) = Upper(:nomeCompleto)")
	public Colaborador buscarPor(String nomeCompleto);
	
	@Modifying
	@Query(value = "UPDATE Colaborador c SET c.status = :status WHERE c.id = :id")
	public void atualizarStatusPor(Integer id, Status status);

}
