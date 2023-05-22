package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.DiasSemanaSanta;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Hermandad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.service.HermandadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;

@Controller
@RequestMapping("/admin/hermandad")
public class HermandadController {

	@Autowired
	private HermandadService hermandadService;
	
	@Autowired
	private PropietarioService propietarioService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@ModelAttribute("dias")
    public DiasSemanaSanta[] getTiposLocalidad() {
        return DiasSemanaSanta.values();
    }
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("hermandades", hermandadService.findAll());
		return "admin/hermandad/list-hermandad";
	}
	
	@GetMapping("/nuevo")
	public String nuevoHermandad(@AuthenticationPrincipal Propietario propietario, Model model) {
		model.addAttribute("propietario", propietario);
		model.addAttribute("hermandad", new Hermandad());
		return "admin/hermandad/form-hermandad";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoSector(@ModelAttribute("sector") Hermandad hermandad, Model model) {
		
		hermandadService.save(hermandad);
		
		return "redirect:/admin/hermandad/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarHermandad(@AuthenticationPrincipal Propietario propietario, @PathVariable("id") Long id, Model model) {
		model.addAttribute("propietario", propietario);
		Optional<Hermandad> hermandadOpt = hermandadService.findById(id);
		if(hermandadOpt.isPresent())
			model.addAttribute("hermandad", hermandadOpt.get());
		return "admin/hermandad/form-hermandad";
		
	}
	
	@GetMapping("/hermanos/{id}")
	public String editarHermanos(@PathVariable("id") Long id, Model model) {
		Optional<Hermandad> hermandadOpt = hermandadService.findById(id);
		if(hermandadOpt.isPresent()) {
			Hermandad hermandad = hermandadOpt.get();
			model.addAttribute("numHermanos", hermandad.getListaHermanos().size());
			model.addAttribute("hermandad", hermandad);
		}
		return "admin/hermandad/list-hermanos";
	}
	
	@GetMapping("/hermanos/borrar/{id}")
	public String borrarHermano(@PathVariable("id") Long id, Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(id);
		if(propietarioOpt.isPresent()) {
			Propietario p = propietarioOpt.get();
			p.setHermandad(null);
			propietarioService.save(p);
		}
		return "redirect:/admin/hermandad/";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarHermandad(@PathVariable("id") Long id, Model model) {
		
		Optional<Hermandad> hermandadOpt = hermandadService.findById(id);
		
		if (hermandadOpt.isPresent()) {
			Hermandad hermandad = hermandadOpt.get();
			for (Propietario p : hermandad.getListaHermanos()) {
				p.setHermandad(null);
			}
			hermandadService.deleteById(id);
		}

		return "redirect:/admin/hermandad/";
		
		
	}
}
