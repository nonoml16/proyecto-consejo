package com.salesianostriana.dam.proyectoconsejohermandades.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.LocalidadRepositorio;

@Service
public class LocalidadService extends BaseServiceImpl<Localidad, Long, LocalidadRepositorio>{

	public int numeroLocalidadesSector(Sector sector) {
		return repository.findNumLocalidadesBySector(sector);
	}
}
