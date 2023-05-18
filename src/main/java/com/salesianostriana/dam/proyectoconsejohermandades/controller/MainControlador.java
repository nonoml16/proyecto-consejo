package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;

@Controller
public class MainControlador {

	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@GetMapping("/")
    public String showUserDetails(Model model) {
        return "index";
    }
}
