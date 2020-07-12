package com.shop.restfull.dto;

import java.util.List;

public class CategoriaPadreDto {
	
	private int id;
	private String nombre;
	private String nombreEng;
	private String key;
	private String modulo;
	private List<CategoriaDto> categorias;
	
	public CategoriaPadreDto() {
	}
	
	public CategoriaPadreDto(int id, String nombre, String nombreEng, String key, String modulo,
			List<CategoriaDto> categorias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreEng = nombreEng;
		this.key = key;
		this.modulo = modulo;
		this.categorias = categorias;
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
	public List<CategoriaDto> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaDto> categorias) {
		this.categorias = categorias;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
}
