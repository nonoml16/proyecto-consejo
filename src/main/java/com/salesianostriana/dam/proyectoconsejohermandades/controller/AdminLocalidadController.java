package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;

@Controller
@RequestMapping("/admin/localidad")
public class AdminLocalidadController {

	@Autowired
	private LocalidadService localidadService;
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("localidades", localidadService.findAll());
		return "admin/localidad/list-localidad";
	}
}
