package com.salesianostriana.dam.proyectoconsejohermandades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;

public interface PropietarioRepositorio extends JpaRepository<Propietario, Long>{

	Optional<Propietario> findFirstByUsername(String username);
}
