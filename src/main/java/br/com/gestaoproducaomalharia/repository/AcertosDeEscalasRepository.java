package br.com.gestaoproducaomalharia.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.AcertoDeEscala;

@Repository
public interface AcertosDeEscalasRepository extends JpaRepository<AcertoDeEscala, Integer> {
	
	@Query(value = 
			"SELECT ae "
			+ "FROM AcertoDeEscala ae "
			+ "JOIN FETCH ae.colaborador c "
			+ "WHERE ae.colaborador.id = :idDoColaborador "
			+ "AND ae.dia = :dia")
	public AcertoDeEscala buscarPor(int idDoColaborador, LocalDate dia);
	
	@Query(value = 
			"SELECT ae "
			+ "FROM AcertoDeEscala ae "
			+ "JOIN FETCH ae.colaborador c "
			+ "WHERE ae.id = :id")
	public AcertoDeEscala buscarPor(Integer id);

}
