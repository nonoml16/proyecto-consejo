package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.service.HermandadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/user")
public class UsuarioControlador {

	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private HermandadService hermandadService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@GetMapping("/editar/{id}")
	public String editarCuenta (@PathVariable("id") Long id, Model model) {
		model.addAttribute("hermandades", hermandadService.findAll());
		return"user/form-perfil";
	}
	
	@PostMapping("/propietario/submit")
	public String procesarFormulario(@ModelAttribute("propietario") Propietario propietario) {
        propietarioService.save(propietario);
        return "redirect:/";
    }

}