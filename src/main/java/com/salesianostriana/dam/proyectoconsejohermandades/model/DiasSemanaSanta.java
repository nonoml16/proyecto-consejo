package com.salesianostriana.dam.proyectoconsejohermandades.model;

public enum DiasSemanaSanta {

	DOMINGO_DE_RAMOS("Domingo de Ramos"), 
	LUNES_SANTO("Lunes Santo"), 
	MARTES_SANTO("Martes Santo"), 
	MIERCOLES_SANTO("Miércoles Santo"), 
	JUEVES_SANTO("Jueves Santo"), 
	MADRUGA("Madrugá"), 
	VIERNES_SANTO("Viernes Santo"), 
	SABADO_SANTO("Sábado Santo"), 
	DOMINGO_DE_RESURRECCION("Domingo de Resurrección");
	
	private final String displayValue;
    
    private DiasSemanaSanta(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
