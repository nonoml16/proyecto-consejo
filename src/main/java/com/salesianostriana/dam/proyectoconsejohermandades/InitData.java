package com.salesianostriana.dam.proyectoconsejohermandades;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
	
	/*private final PropietarioRepositorio repo;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		Propietario usuario = Propietario.builder()
				.id(1L)
				.consejero(false)
				.username("user")
				//.password("1234")
				.password(passwordEncoder.encode("1234"))
				.dni("21135681S")
				.nombre("Antonio")
				.apellidos("Martínez López")
				.build();
		
		Propietario admin = Propietario.builder()
				.id(2L)
				.consejero(true)
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.dni("89088712Y")
				.nombre("Francisco")
				.apellidos("Vélez de Luna")
				.build();
		
		repo.saveAll(List.of(usuario, admin));
		
	}*/

}