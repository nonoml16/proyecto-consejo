package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/admin/localidad")
public class AdminLocalidadController {
	
	@Autowired
	private LocalidadService localidadService;
	
	@Autowired
	private PropietarioService propietarioService;

	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@PostMapping("/nueva/submit")
	public String submitNuevaLocalidad(@ModelAttribute("localidad") Localidad localidad, Model model) {
		
		localidadService.save(localidad);
		
		return "redirect:/admin/sector/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarLocalidad(@PathVariable("id") Long id, Model model) {
		
		Optional<Localidad> localidadOpt = localidadService.findById(id);
		if(localidadOpt.isPresent())
			model.addAttribute("localidad", localidadOpt.get());
		model.addAttribute("propietarios", propietarioService.findAll());
		return "admin/localidad/form-localidad";
		
	}
}
