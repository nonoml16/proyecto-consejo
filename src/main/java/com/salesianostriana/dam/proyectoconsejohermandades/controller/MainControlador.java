package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;

@Controller
public class MainControlador {

	@GetMapping("/")
    public String showUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Propietario usuario = (Propietario) auth.getPrincipal();
        model.addAttribute("nombre", usuario.getNombre());
        model.addAttribute("apellidos", usuario.getApellidos());
        return "index";
    }
}
