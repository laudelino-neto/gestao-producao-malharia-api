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
			+ "WHERE e.colaborador = :colaborador "
			+ "AND e.data = :data")
	public Optional<Escala> buscarPor(Colaborador colaborador, LocalDate data);
	
}
