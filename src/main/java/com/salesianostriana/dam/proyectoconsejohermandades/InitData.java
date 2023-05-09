package com.salesianostriana.dam.proyectoconsejohermandades;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Usuario;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
	
	private final UsuarioRepositorio repo;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		Usuario usuario = Usuario.builder()
				.admin(false)
				.username("user")
				//.password("1234")
				.password(passwordEncoder.encode("1234"))
				.nombre("Manuel")
				.apellidos("Lopez Fernandez")
				.build();
		
		Usuario admin = Usuario.builder()
				.admin(true)
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.nombre("Francisco")
				.apellidos("Vélez")
				.build();
		
		repo.saveAll(List.of(usuario, admin));
		
	}

}