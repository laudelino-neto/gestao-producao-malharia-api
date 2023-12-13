package br.com.gestaoproducaomalharia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.gestaoproducaomalharia.entity.Usuario;
import br.com.gestaoproducaomalharia.repository.UsuariosRepository;
import br.com.gestaoproducaomalharia.security.CredencialDeAcesso;

@Service
public class CredencialDeAcessoServiceImpl implements UserDetailsService{

	@Autowired
	private UsuariosRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {		
		Usuario usuario = repository.buscarPor(login);		
		Preconditions.checkNotNull(usuario, "O login não existe");		
		UserDetails credencial = new CredencialDeAcesso(usuario);		
		return credencial;		
	}

}
