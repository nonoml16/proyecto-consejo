package com.salesianostriana.dam.proyectoconsejohermandades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;

public interface LocalidadRepositorio extends JpaRepository<Localidad, Long>{

	@Query("select count(p) from Localidad l where l.sector = ?1")
	public int findNumLocalidadesBySector(Sector sector);
}
