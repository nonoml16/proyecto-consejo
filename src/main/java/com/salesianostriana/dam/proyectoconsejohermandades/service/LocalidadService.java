package com.salesianostriana.dam.proyectoconsejohermandades.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.LocalidadRepositorio;

@Service
public class LocalidadService extends BaseServiceImpl<Localidad, Long, LocalidadRepositorio>{

	public int numeroLocalidadesSector(Sector sector) {
		return repository.findNumLocalidadesBySector(sector);
	}
	
	public int numeroLocalidadesPropietario(Propietario propietario) {
		return repository.findNumLocalidadesByPropietario(propietario);
	}
	
	public void eliminarLocalidad(Long localidadId) {
		  Optional<Localidad> localidad = findById(localidadId);
		  localidad.get().getPropietario().borrarLocalidad(localidad.get());
		  delete(localidad.get());
		}
}
