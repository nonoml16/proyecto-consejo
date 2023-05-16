package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.PropietarioRepositorio;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/localidad")
public class LocalidadControlador {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private LocalidadService localidadService;
	
	@Autowired
	private PropietarioRepositorio propietarioRepositorio;

	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("localidades", localidadService.findAll());
		return "user/list-localidad";
	}
	
	@GetMapping("/solicitar")
	public String solicitar (Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Propietario usuario = (Propietario) auth.getPrincipal();
		model.addAttribute("localidad", new Localidad());
		model.addAttribute("sectores", sectorService.findAll());
        model.addAttribute("propietario", usuario);
		return "user/form-localidad";
	}
	
	@PostMapping("/solicitar/submit")
    public String altaLocalidadSubmit(@ModelAttribute("localidad") Localidad localidad, Model model) {
		localidadService.save(localidad);
        return "redirect:/localidad/solicitar";
    }
	
	@GetMapping("/misLocalidades")
	public String listLocalidades(Model model) {
		model.addAttribute("localidades", localidadService.findAll());
		return "user/list-localidad";
	}
	
}
