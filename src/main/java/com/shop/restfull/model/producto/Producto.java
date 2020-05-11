package com.shop.restfull.model.producto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.Pedido;
import com.shop.restfull.model.Sabor;

@Entity
@Table(name = "producto")
public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	
	private Double precio;
	
	private Double tamano;
	
	@ManyToMany(mappedBy = "productos")
	private List<Sabor> sabores;
	
	@Column(name = "sabor_seleccionado")
	private String saborSeleccionado;
	
	private Integer cantidad;
	
	private Double puntuacion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.LAZY)
	private List<Comentario> comentarios;
	
	private Boolean disponible;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
	private List<Foto> fotos;
	
	private Double descuento;
	
	@Column(name = "precio_final")
	private Double precioFinal;
	
	@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
	private ValorNutricional valorNutricional;
	
	@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
	private Descripcion descripcion;
	
	@ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;
	
	@ManyToMany(mappedBy = "productos")
    private List<Cesta> cesta;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORIA_PRODUCT", referencedColumnName = "id")
	@JsonIgnore
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "FK_SUB_CATEGORIA_PRODUCT", referencedColumnName = "id")
	@JsonIgnore
	private SubCategoria subCategoria;
	
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

	public List<Sabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}

	public String getSaborSeleccionado() {
		return saborSeleccionado;
	}

	public void setSaborSeleccionado(String saborSeleccionado) {
		this.saborSeleccionado = saborSeleccionado;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public ValorNutricional getValorNutricional() {
		return valorNutricional;
	}

	public void setValorNutricional(ValorNutricional valorNutricional) {
		this.valorNutricional = valorNutricional;
	}

	public Descripcion getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(Descripcion descripcion) {
		this.descripcion = descripcion;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Cesta> getCesta() {
		return cesta;
	}

	public void setCesta(List<Cesta> cesta) {
		this.cesta = cesta;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	
}
