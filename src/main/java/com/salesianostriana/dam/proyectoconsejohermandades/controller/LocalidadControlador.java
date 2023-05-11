package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/localidad")
public class LocalidadControlador {

	@GetMapping("/solicitar")
	public String solicitar (Model model) {
		return "user/form-localidad";
	}
}
