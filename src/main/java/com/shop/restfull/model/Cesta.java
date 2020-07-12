package com.shop.restfull.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.producto.ProductoCesta;

@Entity
@Table(name = "cesta")
public class Cesta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Integer cantidadProductos;
	
	@Column(name="importe_total")
	private Double importeTotal;
	
	@Column(name="importe_subtotal")
	private Double importeSubTotal;
	
	private Double envio;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USUARIO", updatable = false, nullable = false, unique = true)
    private Usuario usuario;
	
	@OneToMany(mappedBy = "cesta")
    private Set<ProductoCesta> productosCesta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	
	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getImporteSubTotal() {
		return importeSubTotal;
	}

	public void setImporteSubTotal(Double importeSubTotal) {
		this.importeSubTotal = importeSubTotal;
	}

	public Double getEnvio() {
		return envio;
	}

	public void setEnvio(Double envio) {
		this.envio = envio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@JsonIgnore
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<ProductoCesta> getProductosCesta() {
		return productosCesta;
	}

	public void setProductosCesta(Set<ProductoCesta> productosCesta) {
		this.productosCesta = productosCesta;
	}

	public void removeProductoCesta(ProductoCesta productoCesta) {
		this.productosCesta.remove(productoCesta);
		//productoCesta.getCesta().remove(this);
	}
	public void addPhone(ProductoCesta productoCesta) {
	     if (productoCesta != null) {
	        if (this.productosCesta == null) {
	            this.productosCesta = new HashSet<ProductoCesta>();          
	        }
	        this.productosCesta.add(productoCesta);
	        productoCesta.setCesta(this);
	     }
	  }

}
