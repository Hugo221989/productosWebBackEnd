package com.shop.restfull.dto;

import org.springframework.hateoas.RepresentationModel;

public class ManagerMenuItemDto extends RepresentationModel<CustomerBasicDto>{
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
