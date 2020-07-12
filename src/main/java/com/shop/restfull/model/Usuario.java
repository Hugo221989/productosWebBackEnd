package com.shop.restfull.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	
	private String apellido;
	
	private String usuario;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nacimiento;
	
	private String email;
	
	private String telefono;
	
	private Boolean admin;
	
	@Column(name = "email_confirmado")
	private Boolean emailConfirmado;
	
	@ManyToOne
	@JoinColumn(name = "genero")
	private Genero genero;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private UsuarioDireccion direccion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	@JsonIgnore
    private List<Pedido> pedidos;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
    private Cesta cesta;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
    private RegistrationToken registrationToken;
	
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

	public Boolean getAdmin() {
		return admin;
	}

	public Boolean getEmailConfirmado() {
		return emailConfirmado;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean isEmailConfirmado() {
		return emailConfirmado;
	}
	public void setEmailConfirmado(Boolean emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}
	@JsonIgnore
	public Cesta getCesta() {
		return cesta;
	}
	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public RegistrationToken getRegistrationToken() {
		return registrationToken;
	}
	public void setRegistrationToken(RegistrationToken registrationToken) {
		this.registrationToken = registrationToken;
	}
	
	
}
