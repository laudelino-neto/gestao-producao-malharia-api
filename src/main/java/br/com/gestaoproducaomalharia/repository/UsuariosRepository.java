package br.com.gestaoproducaomalharia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gestaoproducaomalharia.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, String>{

	@Query(value = 
			"SELECT u "
			+ "FROM Usuario u "
			+ "WHERE u.login = :login ")
	public Usuario buscarPor(String login);
	
}
