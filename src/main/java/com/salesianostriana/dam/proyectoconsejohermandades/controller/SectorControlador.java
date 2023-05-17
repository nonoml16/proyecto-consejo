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

import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/admin/sector")
public class SectorControlador {

	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private LocalidadService localidadService;
	
	/*@Autowired
	private LocalidadService localidadService;*/
	
	@GetMapping("/")
	public String index (@AuthenticationPrincipal Propietario propietario, Model model) {
		model.addAttribute("propietario", propietario);
		model.addAttribute("sectores", sectorService.findAll());
		return "admin/sector/list-sector";
	}
	
	@GetMapping("/nuevo")
	public String nuevoSector(Model model) {
		model.addAttribute("sector", new Sector());
		return "admin/sector/form-sector";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoSector(@ModelAttribute("sector") Sector sector, Model model) {
		
		sectorService.save(sector);
		
		return "redirect:/admin/sector/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarSector(@AuthenticationPrincipal Propietario propietario, @PathVariable("id") Long id, Model model) {
		model.addAttribute("propietario", propietario);
		Optional<Sector> sectorOpt = sectorService.findById(id);
		if(sectorOpt.isPresent())
			model.addAttribute("sector", sectorOpt.get());
		return "admin/sector/form-sector";
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarSector(@PathVariable("id") Long id, Model model) {
		
		Optional<Sector> sector = sectorService.findById(id);
		
		if (sector.isPresent())
			if (localidadService.numeroLocalidadesSector(sector.get()) == 0)
				sectorService.deleteById(id);

		return "redirect:/admin/sector/";
		
		
	}
}
