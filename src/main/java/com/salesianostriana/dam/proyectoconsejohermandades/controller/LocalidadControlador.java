package com.salesianostriana.dam.proyectoconsejohermandades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/localidad")
public class LocalidadControlador {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private LocalidadService localidadService;

	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("localidades", localidadService.findAll());
		return "user/list-localidad";
	}
	
	@GetMapping("/solicitar")
	public String solicitar (Model model) {
		model.addAttribute("localidad", new Localidad());
		model.addAttribute("sectores", sectorService.findAll());
		return "user/form-localidad";
	}
	
	@PostMapping("/solicitar/submit")
    public String altaLocalidadSubmit(Localidad localidad, Model model) {
        localidadService.save(localidad);
        return "redirect:/localidad/solicitar";
    }
	
	@GetMapping("/misLocalidades")
	public String listLocalidades(Model model) {
		model.addAttribute("localidades", localidadService.findAll());
		return "user/list-localidad";
	}
	
}
