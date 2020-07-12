package com.shop.restfull.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "metodo_pago")
public class MetodoPago implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	
	@Column(name = "nombre_eng")
	private String nombreEng;
	
	private String descripcion;
	
	@Column(name = "descripcion_eng")
	private String descripcionEng;
	
	@OneToMany(mappedBy = "metodoPago")
	@JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();
	
	private String icono;

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

	public String getNombreEng() {
		return nombreEng;
	}

	public void setNombreEng(String nombreEng) {
		this.nombreEng = nombreEng;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionEng() {
		return descripcionEng;
	}

	public void setDescripcionEng(String descripcionEng) {
		this.descripcionEng = descripcionEng;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}


}
