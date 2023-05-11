package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Usuario;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
	
	@GetMapping("/")
	public String admin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        model.addAttribute("nombre", usuario.getNombre());
        model.addAttribute("apellidos", usuario.getApellidos());
		return "admin";
	}

}
