package com.shop.restfull.model.producto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.Sabor;

@Entity
@Table(name = "producto_cesta")
public class ProductoCesta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "FK_SABOR_PRODUCT_CESTA", referencedColumnName = "id")
	private Sabor saborSeleccionado;
	@ManyToOne
	@JoinColumn(name = "FK_PRODUCTO_PRODUCT_CESTA", referencedColumnName = "id")
	private Producto producto;
	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "FK_CESTA", referencedColumnName = "id")
	@JsonIgnore
	private Cesta cesta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sabor getSaborSeleccionado() {
		return saborSeleccionado;
	}
	public void setSaborSeleccionado(Sabor saborSeleccionado) {
		this.saborSeleccionado = saborSeleccionado;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public Cesta getCesta() {
		return cesta;
	}
	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	

}
