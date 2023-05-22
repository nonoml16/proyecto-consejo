package com.salesianostriana.dam.proyectoconsejohermandades.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hermandad {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private DiasSemanaSanta dia;
	
	@OneToMany(mappedBy = "hermandad")
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Propietario> listaHermanos = new ArrayList<Propietario>();
}
