package com.salesianostriana.dam.proyectoconsejohermandades.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Sector {

	@Id
	@GeneratedValue
	private Long id;
	
	private String localizacion;
	private double precio;
	
	@OneToMany(mappedBy = "sector", fetch = FetchType.EAGER,
			cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Localidad> localidades = new ArrayList<>();
}
