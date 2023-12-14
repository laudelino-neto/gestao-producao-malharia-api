package br.com.gestaoproducaomalharia.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Escala;

@Repository
public interface EscalasRepository extends JpaRepository<Escala, Integer> {
	
	@Query(value = "SELECT e "
			+ "FROM Escala e "
			+ "WHERE e.colaborador.id = :idDoColaborador "
			+ "AND e.data = :data")
	public Escala buscarPor(int idDoColaborador, LocalDate data);
	
}
