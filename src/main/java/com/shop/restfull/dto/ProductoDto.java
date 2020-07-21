package com.shop.restfull.dto;

import com.shop.restfull.model.producto.Foto;

public class ProductoDto {

	private int id;
	private String categoriaPadre;
	private String categoria;
	private String subCategoria;
	private String nombre;
	private String nombreEng;
	private Double precio;
	private Double tamano;
	private Double puntuacion;
	private Boolean disponible;
	private Double descuento;
	private Double precioFinal;
	private String descripcion;
	private Foto foto;
	
	
	
	public ProductoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductoDto(int id, String categoriaPadre, String categoria, String subCategoria, String nombre,
			String nombreEng, Double precio, Double tamano, Double puntuacion, Boolean disponible, Double descuento,
			Double precioFinal, String descripcion, Foto foto) {
		super();
		this.id = id;
		this.categoriaPadre = categoriaPadre;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
		this.nombre = nombre;
		this.nombreEng = nombreEng;
		this.precio = precio;
		this.tamano = tamano;
		this.puntuacion = puntuacion;
		this.disponible = disponible;
		this.descuento = descuento;
		this.precioFinal = precioFinal;
		this.descripcion = descripcion;
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoriaPadre() {
		return categoriaPadre;
	}
	public void setCategoriaPadre(String categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
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
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getTamano() {
		return tamano;
	}
	public void setTamano(Double tamano) {
		this.tamano = tamano;
	}
	public Double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public Double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
