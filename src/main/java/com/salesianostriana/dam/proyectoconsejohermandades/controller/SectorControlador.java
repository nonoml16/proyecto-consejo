package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
public class SectorControlador {

	@Autowired
	private SectorService sectorService;
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("sectores", sectorService.findAll());
		return "index";
	}
}
