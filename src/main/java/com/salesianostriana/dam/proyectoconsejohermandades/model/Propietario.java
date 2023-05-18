package com.salesianostriana.dam.proyectoconsejohermandades.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Propietario extends Usuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dni;
	
	private String nombre, apellidos;
	
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_propietario_hermandad"))
	private Hermandad hermandad;
	
	@OneToMany(mappedBy = "propietario")
	//@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Localidad> localidades = new ArrayList<>();

	public Propietario(Long id, String username, String password, boolean consejero, 
			String dni, String nombre, String apellidos, List<Localidad> localidades) {
		super(id, username, password, consejero);
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.localidades = localidades;
	}

}
