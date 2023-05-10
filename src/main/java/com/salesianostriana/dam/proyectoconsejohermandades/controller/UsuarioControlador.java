package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Usuario;

@Controller
public class UsuarioControlador {

	@GetMapping("/")
    public String showUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        model.addAttribute("nombre", usuario.getNombre());
        model.addAttribute("apellidos", usuario.getApellidos());
        return "index";
    }
	
	@GetMapping("/me")
	public String me() {

		Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println(u.toString());

		return "perfil";
	}

	@GetMapping("/me2")
	public String me2(@AuthenticationPrincipal Usuario u) {

		System.out.println(u.toString());

		return "perfil";
	}

}