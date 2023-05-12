package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/admin/propietario")
public class PropietarioController {

	@Autowired
	private PropietarioService propietarioService;
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("propietarios", propietarioService.findAll());
		return "admin/propietario/list-propietario";
	}
	
	@GetMapping("/nuevo")
	public String nuevoPropietario(Model model) {
		model.addAttribute("propietario", new Propietario());
		return "admin/propietario/form-propietario";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoPropietario(@ModelAttribute("propietario") Propietario propietario, Model model) {
		
		propietarioService.save(propietario);
		
		return "redirect:/admin/propietario/";
	}
	
	@GetMapping("/editar/{dni}")
	public String editarSector(@PathVariable("dni") String dni, Model model) {
		
		
		model.addAttribute("propietario", propietarioService.findById(dni));
			return "admin/sector/form-propietario";
		
	}
}
