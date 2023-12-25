package br.com.gestaoproducaomalharia.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.entity.Escala;

@Repository
public interface EscalasRepository extends JpaRepository<Escala, Integer>{

	@Query(value = 
			"SELECT e "
			+ "FROM Escala e "
			+ "JOIN FETCH e.colaborador c "
			+ "WHERE e.colaborador = :colaborador "
			+ "AND e.data = :data")
	public Optional<Escala> buscarPor(Colaborador colaborador, LocalDate data);
	
	@Query(value = 
			"SELECT e "
			+ "FROM Escala e "
			+ "JOIN FETCH e.colaborador c "
			+ "WHERE e.id = :id")
	public Optional<Escala> buscarPor(Integer id);
	
}
