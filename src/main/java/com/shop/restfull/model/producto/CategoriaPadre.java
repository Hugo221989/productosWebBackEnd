package com.shop.restfull.model.producto;

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

@Entity
@Table(name = "categoria_padre")
public class CategoriaPadre implements Serializable{

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
	
	@OneToMany(mappedBy = "categoriaPadre")
    private List<Categoria> categoria = new ArrayList<>();
	
	@OneToMany(mappedBy = "categoriaPadre")
    private List<Producto> productos = new ArrayList<>();
	
	private String kkey;
	
	private String modulo;

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

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	public String getKkey() {
		return kkey;
	}

	public void setKkey(String kkey) {
		this.kkey = kkey;
	}

	public String getNombreEng() {
		return nombreEng;
	}

	public void setNombreEng(String nombreEng) {
		this.nombreEng = nombreEng;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
}
