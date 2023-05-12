package com.salesianostriana.dam.proyectoconsejohermandades.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Localidad {

	@Id
	@GeneratedValue
	private Long id;
	
	private int fila, numLocalidad;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_localidad_propietario"))
	private Propietario propietario;
	
	@Enumerated(EnumType.STRING)
	private TipoLocalidad tipoLocalidad;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_localidad_sector"))
	private Sector sector;
	
}
