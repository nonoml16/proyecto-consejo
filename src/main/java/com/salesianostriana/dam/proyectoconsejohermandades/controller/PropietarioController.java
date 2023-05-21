package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.LocalidadRepositorio;
import com.salesianostriana.dam.proyectoconsejohermandades.service.HermandadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/admin/propietario")
public class PropietarioController {

	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private LocalidadRepositorio localidadRepositorio;
	
	@Autowired
	private HermandadService hermandadService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("propietarios", propietarioService.findAll());
		return "admin/propietario/list-propietario";
	}
	
	@GetMapping("/nuevo")
	public String nuevoPropietario(Model model) {
		model.addAttribute("hermandades", hermandadService.findAll());
		model.addAttribute("propietario", new Propietario());
		return "admin/propietario/form-propietario";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoPropietario(@ModelAttribute("propietario") Propietario propietario, Model model) {
		propietario.setPassword(passwordEncoder.encode(propietario.getPassword()));
		propietarioService.save(propietario);
		
		return "redirect:/admin/propietario/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarPropietario(@PathVariable("id") Long id, Model model) {
		
		Optional<Propietario> propietario = propietarioService.findById(id);
		if(propietario.isPresent())
			model.addAttribute("propietario", propietario.get());
		return "admin/propietario/form-propietario";
		
	}
	/*
	@GetMapping("/borrar/{id}")
	public String borrarPropietario(@PathVariable("id") Long id,  Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(id);
		if(propietarioOpt.isPresent()) {
			if(localidadService.numeroLocalidadesPropietario(propietarioOpt.get()) == 0)
				propietarioService.deleteById(id);
			
		}
		
		return "redirect:/admin/propietario/";
	}
	*/
	@GetMapping("/borrar/{id}")
	public String borrarPropietario(@PathVariable("id") Long id,  Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(id);
		if(propietarioOpt.isPresent()) {
			localidadRepositorio.deleteAll(propietarioOpt.get().getLocalidades());
			propietarioService.deleteById(id);
		}
		
		return "redirect:/admin/propietario/";
	}
}
