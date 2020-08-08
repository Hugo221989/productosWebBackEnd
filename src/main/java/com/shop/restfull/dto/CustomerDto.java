package com.shop.restfull.dto;

import java.util.Date;
import org.springframework.hateoas.RepresentationModel;
import com.shop.restfull.model.Genero;
import com.shop.restfull.model.UsuarioDireccion;

public class CustomerDto extends RepresentationModel<CustomerDto> {
	
	private int id;
	
	private String nombre;
	
	private String apellido;
	
	private String usuario;
	
	private Date nacimiento;
	
	private String email;
	
	private String telefono;
	
	private Boolean admin;
	
	private Genero genero;
	
    private UsuarioDireccion direccion;

	public CustomerDto() {
		super();
	}

	public CustomerDto(int id, String nombre, String apellido, String usuario, Date nacimiento, String email,
			String telefono, Boolean admin, Genero genero, UsuarioDireccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.nacimiento = nacimiento;
		this.email = email;
		this.telefono = telefono;
		this.admin = admin;
		this.genero = genero;
		this.direccion = direccion;
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

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public UsuarioDireccion getDireccion() {
		return direccion;
	}

	public void setDireccion(UsuarioDireccion direccion) {
		this.direccion = direccion;
	}
	
	

}
