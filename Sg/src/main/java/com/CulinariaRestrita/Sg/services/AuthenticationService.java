package com.CulinariaRestrita.Sg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CulinariaRestrita.Sg.repositories.UsersRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UsersRepository usuarioRepository;

	/* Ira realizar uma busca na tabela filtrando pelo email */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByemail(username);
	}
}