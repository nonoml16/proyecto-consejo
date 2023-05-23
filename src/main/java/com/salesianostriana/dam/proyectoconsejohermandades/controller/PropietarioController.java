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

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.service.HermandadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/admin/propietario")
public class PropietarioController {

	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private HermandadService hermandadService;
	
	@Autowired
	private LocalidadService localidadService;
	
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
		model.addAttribute("hermandades", hermandadService.findAll());
		if(propietario.isPresent())
			model.addAttribute("propietario", propietario.get());
		return "admin/propietario/form-propietario";
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarPropietario(@PathVariable("id") Long id,  Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(id);
		if(propietarioOpt.isPresent()) {
			Propietario p = propietarioOpt.get();
			for (Localidad l : p.getLocalidades()) {
				l.setPropietario(null);
			}
				
			propietarioService.deleteById(id);
		}
		
		return "redirect:/admin/propietario/";
	}
	
	@GetMapping("/localidades/{id}")
	public String mostrarLocalidadesPropietario(@PathVariable("id") Long id, Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(id);
		if(propietarioOpt.isPresent())
			model.addAttribute("propietario", propietarioOpt.get());
		return "admin/propietario/list-localidad";
	}
	
	@GetMapping("/localidad/liberar/{id}")
	public String liberarLocalidad(@PathVariable("id") Long id, Model model) {
		Optional<Localidad> localidadOpt = localidadService.findById(id);
		if(localidadOpt.isPresent()) {
			Localidad l = localidadOpt.get();
			l.setPropietario(null);
			localidadService.save(l);
		}
		return "redirect:/admin/propietario/";
	}
}
