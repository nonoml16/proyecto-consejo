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

import com.salesianostriana.dam.proyectoconsejohermandades.model.Hermandad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.service.HermandadService;

@Controller
@RequestMapping("/admin/hermandad")
public class HermandadController {

	@Autowired
	private HermandadService hermandadService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("hermandades", hermandadService.findAll());
		return "admin/hermandad/list-hermandad";
	}
	
	@GetMapping("/nuevo")
	public String nuevoHermandad(@AuthenticationPrincipal Propietario propietario, Model model) {
		model.addAttribute("propietario", propietario);
		model.addAttribute("hermandad", new Hermandad());
		return "admin/hermandad/form-hermandad";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoSector(@ModelAttribute("sector") Hermandad hermandad, Model model) {
		
		hermandadService.save(hermandad);
		
		return "redirect:/admin/hermandad/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarHermandad(@AuthenticationPrincipal Propietario propietario, @PathVariable("id") Long id, Model model) {
		model.addAttribute("propietario", propietario);
		Optional<Hermandad> hermandadOpt = hermandadService.findById(id);
		if(hermandadOpt.isPresent())
			model.addAttribute("hermandad", hermandadOpt.get());
		return "admin/hermandad/form-hermandad";
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarHermandad(@PathVariable("id") Long id, Model model) {
		
		Optional<Hermandad> hermandad = hermandadService.findById(id);
		
		if (hermandad.isPresent())
			hermandadService.deleteById(id);

		return "redirect:/admin/hermandad/";
		
		
	}
}
