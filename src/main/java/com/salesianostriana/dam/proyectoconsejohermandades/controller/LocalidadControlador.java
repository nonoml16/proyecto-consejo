package com.salesianostriana.dam.proyectoconsejohermandades.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectoconsejohermandades.model.Localidad;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Propietario;
import com.salesianostriana.dam.proyectoconsejohermandades.model.Sector;
import com.salesianostriana.dam.proyectoconsejohermandades.model.TipoLocalidad;
import com.salesianostriana.dam.proyectoconsejohermandades.repositories.LocalidadRepositorio;
import com.salesianostriana.dam.proyectoconsejohermandades.service.LocalidadService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.PropietarioService;
import com.salesianostriana.dam.proyectoconsejohermandades.service.SectorService;

@Controller
@RequestMapping("/localidad")
public class LocalidadControlador {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private LocalidadService localidadService;
	
	@Autowired
	private LocalidadRepositorio localidadRepositorio;
	
	@Autowired
	private PropietarioService propietarioService;
	
	@ModelAttribute("propietario")
	public Propietario usuario (@AuthenticationPrincipal Propietario propietario) {
		return propietario;
	}
	
	@ModelAttribute("tiposLocalidad")
    public TipoLocalidad[] getTiposLocalidad() {
        return TipoLocalidad.values();
    }
	
	@GetMapping("/{id}")
	public String index (@AuthenticationPrincipal Propietario propietario, Model model) {
		Optional<Propietario> propietarioOpt = propietarioService.findById(propietario.getId());
		if(propietarioOpt.isPresent())
			model.addAttribute("propietario", propietarioOpt.get());
		return "user/list-localidad";
	}
	
	@GetMapping("/sectores")
    public String mostrarFormulario(Model model) {
        model.addAttribute("sectores", sectorService.findAll());
        return "user/select-sector";
    }
	
	@PostMapping("/sectores/seleccion")
	public String procesarSeleccion(@RequestParam("sectorId") Long sectorId, HttpSession session) {
        session.setAttribute("sectorId", sectorId);
        return "redirect:/localidad/localidades";
    }
	
	@GetMapping("/localidades")
	public String mostrarLocalidadesDisponibles(Model model, HttpSession session) {
        Long sectorId = (Long) session.getAttribute("sectorId");
        List<Localidad> localidadesDisponibles = localidadRepositorio.findByPropietarioIsNullAndSector(sectorService.findById(sectorId).get());
        model.addAttribute("localidadesDisponibles", localidadesDisponibles);
        return "user/form-localidad";
    }
	
	@PostMapping("/localidades/asignar")
	public String asignarPropietario(@RequestParam("localidadId") Long localidadId, @AuthenticationPrincipal Propietario propietario, HttpSession session) {
	    Long sectorId = (Long) session.getAttribute("sectorId");
	    Optional<Sector> sectorOpt = sectorService.findById(sectorId);
	    Optional<Localidad> localidadOpt = localidadService.findById(localidadId);
	    if(sectorOpt.isPresent()) {
	    	if(localidadOpt.isPresent()) {
	    		Sector s = sectorOpt.get();
	    		Localidad l = localidadOpt.get();
	    		if (l.getPropietario() == null) {
	    			l.setPropietario(propietario);
	    			localidadService.save(l);
	    			s.getLocalidades().set(s.getLocalidades().indexOf(l), localidadService.findById(l.getId()).get());
	    	        sectorService.save(s);
	    	    }
	    	}
	    }

	    return "redirect:/";
	}
	
}
