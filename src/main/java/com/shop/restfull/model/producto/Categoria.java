package com.shop.restfull.model.producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "categoria")
public class Categoria implements Serializable{

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
	
	@OneToMany(mappedBy = "categoria")
    private List<SubCategoria> subCategoria = new ArrayList<>();

	@OneToMany(mappedBy = "categoria")
    private List<Producto> productos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORIA_PADRE", referencedColumnName = "id")
	@JsonIgnore
	private CategoriaPadre categoriaPadre;
	
	private String kkey;

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

	public List<SubCategoria> getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(List<SubCategoria> subCategoria) {
		this.subCategoria = subCategoria;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public CategoriaPadre getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(CategoriaPadre categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
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
	
	
}
