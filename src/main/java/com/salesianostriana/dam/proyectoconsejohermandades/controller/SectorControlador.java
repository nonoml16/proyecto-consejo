package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/admin/sector")
public class SectorControlador {

	@Autowired
	private SectorService sectorService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("sectores", sectorService.findAll());
		return "admin/sector/list-sector";
	}
	
	@GetMapping("/nuevo")
	public String nuevoSector(Model model) {
		model.addAttribute("sector", new Sector());
		return "admin/sector/form-sector";
	}
	
	@PostMapping("/nuevo/submit")
	public String submitNuevoSector(@ModelAttribute("sector") Sector sector, @RequestParam("numFilas") int numFilas,
            @RequestParam("numLocalidades") int numLocalidades, @RequestParam("tipo") String tipo, Model model) {
		
		
		List<Localidad> localidades = new ArrayList<>();
	    System.out.println(tipo);
	    for (int fila = 1; fila <= numFilas; fila++) {
	        for (int numero = 1; numero <= numLocalidades; numero++) {
	            Localidad localidad = new Localidad();
	            localidad.setPropietario(null);
	            localidad.setFila(fila);
	            localidad.setNumLocalidad(numero);
	            localidad.setTipoLocalidad(TipoLocalidad.valueOf(tipo));
	            localidad.setSector(sector);
	            localidades.add(localidad);
	        }
	    }
	    sector.setLocalidades(localidades);
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
			sectorService.deleteById(id);

		return "redirect:/admin/sector/";
	}
	
	@GetMapping("/localidades/{id}")
	public String mostrarLocalidadesSector(@PathVariable("id") Long id, Model model) {
		Optional<Sector> sector = sectorService.findById(id);
		if (sector.isPresent())
			model.addAttribute("sector", sector.get());
		return "admin/localidad/list-localidad";
	}
}
