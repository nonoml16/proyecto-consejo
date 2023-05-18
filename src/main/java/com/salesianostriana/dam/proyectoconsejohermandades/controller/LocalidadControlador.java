package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/localidad")
public class LocalidadControlador {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private LocalidadService localidadService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@GetMapping("/")
	public String index (Model model, Propietario propietario) {
		model.addAttribute("localidades", localidadService.findAll());
		return "user/list-localidad";
	}
	/*
	@GetMapping("/solicitar")
	public String solicitar (Model model, Principal principal) {
		model.addAttribute("sectores", sectorService.findAll());
		Optional<Propietario> propietario = propietarioRepositorio.findByUsername(principal.getName());
		model.addAttribute("propietario", propietario.get());
		model.addAttribute("localidad", new Localidad());
		return "user/form-localidad";
	}
	
	@PostMapping("/solicitar/submit")
    public String altaLocalidadSubmit(@ModelAttribute("localidad") Localidad localidad) {
		localidadService.save(localidad);
        return "redirect:/localidad/";
    }
	*/
	
	@GetMapping("/solicitar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("localidad", new Localidad());
        model.addAttribute("sectores", sectorService.findAll());
        return "user/form-localidad";
    }
	@PostMapping("/solicitar/submit")
	public String procesarFormulario(@ModelAttribute("localidad") Localidad localidad, @AuthenticationPrincipal Propietario propietario) {
        localidad.setPropietario(propietario);
        localidadService.save(localidad);
        return "redirect:/localidad/";
    }
	
}
