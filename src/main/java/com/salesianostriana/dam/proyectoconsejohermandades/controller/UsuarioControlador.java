package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;

@Controller
@RequestMapping("/user")
public class UsuarioControlador {

	
	
	@GetMapping("/me")
	public String me() {

		Propietario u = (Propietario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println(u.toString());

		return "perfil";
	}

	@GetMapping("/me2")
	public String me2(@AuthenticationPrincipal Propietario u) {

		System.out.println(u.toString());

		return "perfil";
	}

}