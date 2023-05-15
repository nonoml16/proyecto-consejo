package com.salesianostriana.dam.proyectoconsejohermandades.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hermandad {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre, dia;
	
	private List<Propietario> listaHermanos;
}
