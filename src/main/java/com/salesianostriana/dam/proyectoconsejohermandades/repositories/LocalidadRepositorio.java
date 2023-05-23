package com.salesianostriana.dam.proyectoconsejohermandades.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;

public interface LocalidadRepositorio extends JpaRepository<Localidad, Long>{

	@Query("select count(l) from Localidad l where l.sector = ?1")
	public int findNumLocalidadesBySector(Sector sector);
	
	@Query("select count(l) from Localidad l where l.propietario = ?1")
	public int findNumLocalidadesByPropietario(Propietario propietario);
	
	List<Localidad> findByPropietarioIsNullAndSector(Sector sector);
}
