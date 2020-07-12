package com.shop.restfull.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.model.producto.ProductoCesta;

@Entity
@Table(name = "sabor")
public class Sabor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String sabor;
	
	@Column(name = "sabor_eng")
	private String saborEng;
	
	@JoinTable(
	        name = "rel_sabores_productos",
	        joinColumns = @JoinColumn(name = "FK_SABOR", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="FK_PRODUCTO", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Producto> productos;
	
	@OneToMany(mappedBy = "saborSeleccionado")
	@JsonIgnore
    private List<ProductoCesta> productosSabor = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getSaborEng() {
		return saborEng;
	}

	public void setSaborEng(String saborEng) {
		this.saborEng = saborEng;
	}

	public List<ProductoCesta> getProductosSabor() {
		return productosSabor;
	}

	public void setProductosSabor(List<ProductoCesta> productosSabor) {
		this.productosSabor = productosSabor;
	}

}
