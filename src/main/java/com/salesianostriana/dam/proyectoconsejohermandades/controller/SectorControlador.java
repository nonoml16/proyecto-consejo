package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/admin/sector")
public class SectorControlador {

	@Autowired
	private SectorService sectorService;
	
	/*@Autowired
	private LocalidadService localidadService;*/
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("sectores", sectorService.findAll());
		return "sector/sector";
	}
	
	@GetMapping("/nuevo")
	public String nuevaCategoria(Model model) {
		model.addAttribute("sector", new Sector());
		return "sector/form-sector";
	}
	
	@PostMapping("/nueva/submit")
	public String submitNuevaCategoria(@ModelAttribute("sector") Sector categoria, Model model) {
		
		sectorService.save(categoria);
		
		return "redirect:/admin/sector/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarSector(@PathVariable("id") Long id, Model model) {
		
		Optional<Sector> sector = sectorService.findById(id);
		
		if (sector != null) {
			model.addAttribute("sector", sector);
			return "sector/form-sector";
		} else {
			return "redirect:/sector/sector/";
		}
		
	}
	
	/*@GetMapping("/borrar/{id}")
	public String borrarCategoria(@PathVariable("id") Long id, Model model) {
		
		Sector sector = sectorService.findById(id);
		
		if (sector != null) {
			
			if (localidadService.numeroProductosCategoria(sector) == 0) {
				sectorService.delete(sector);				
			} else {
				
			//Se ha agregado el parámetro error con valor true a la ruta	
				return "redirect:/admin/categoria/?error=true";
			}
			
		} 

		return "redirect:/admin/categoria/";
		
		
	}*/
}
