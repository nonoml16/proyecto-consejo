package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;

@Controller
public class MainControlador {

	@GetMapping("/")
    public String showUserDetails(@AuthenticationPrincipal Propietario propietario, Model model) {
		model.addAttribute("propietario", propietario);
        return "index";
    }
}
