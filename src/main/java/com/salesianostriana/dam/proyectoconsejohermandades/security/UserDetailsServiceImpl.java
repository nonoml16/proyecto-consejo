package com.salesianostriana.dam.proyectoconsejohermandades.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoconsejohermandades.repositories.PropietarioRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final PropietarioRepositorio repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findFirstByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Error al buscar el usuario"));
	}

}
