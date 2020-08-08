package com.shop.restfull.dto;

import org.springframework.hateoas.RepresentationModel;

public class CustomerBasicDto extends RepresentationModel<CustomerBasicDto> {

	private int id;
	private String nombre;
	private String apellido;
	private String usuario;
	private String email;
	
	public CustomerBasicDto() {
		super();
	}
	
	public CustomerBasicDto(int id, String nombre, String apellido, String usuario, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
