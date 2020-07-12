package com.shop.restfull.dto;

public class SubCategoriaDto {
	private int id;
	private String nombre;
	private String nombreEng;
	private String key;
	public SubCategoriaDto() {
		
	}
	public SubCategoriaDto(int id, String nombre, String nombreEng, String key) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreEng = nombreEng;
		this.key = key;
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
	
}
