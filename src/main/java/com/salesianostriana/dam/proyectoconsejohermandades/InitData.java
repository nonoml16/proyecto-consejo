package com.salesianostriana.dam.proyectoconsejohermandades;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.PropietarioRepositorio;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
	
	private final PropietarioRepositorio repo;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		Propietario usuario = Propietario.builder()
				.esConsejero(false)
				.username("user")
				//.password("1234")
				.password(passwordEncoder.encode("1234"))
				.dni("21135681S")
				.nombre("Antonio")
				.apellidos("Martínez López")
				.build();
		
		Propietario admin = Propietario.builder()
				.esConsejero(true)
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.dni("89088712Y")
				.nombre("Francisco")
				.apellidos("Vélez de Luna")
				.build();
		
		repo.saveAll(List.of(usuario, admin));
		
	}

}