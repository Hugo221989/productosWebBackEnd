package com.shop.restfull.dto;

import java.util.List;

public class CategoriaDto {
	private int id;
	private String nombre;
	private String nombreEng;
	private String key;
	private List<SubCategoriaDto> subcategorias;
	public CategoriaDto() {
		
	}
	public CategoriaDto(int id, String nombre, String nombreEng, String key, List<SubCategoriaDto> subcategorias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreEng = nombreEng;
		this.key = key;
		this.subcategorias = subcategorias;
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
	public String getNombreEng() {
		return nombreEng;
	}
	public void setNombreEng(String nombreEng) {
		this.nombreEng = nombreEng;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<SubCategoriaDto> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<SubCategoriaDto> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
}
