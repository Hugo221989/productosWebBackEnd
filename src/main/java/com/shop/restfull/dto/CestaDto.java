package com.shop.restfull.dto;

import java.util.Set;

import com.shop.restfull.model.producto.ProductoCesta;

public class CestaDto {
	
	private int id;
	private Integer cantidadProductos;
	private Double importeTotal;
	private Double importeSubTotal;
	private Double envio;
	private Set<ProductoCesta> productosCesta;
	
	public CestaDto(int id, Integer cantidadProductos, Double importeTotal, Double importeSubTotal, Double envio,
			Set<ProductoCesta> productosCesta) {
		this.id = id;
		this.cantidadProductos = cantidadProductos;
		this.importeTotal = importeTotal;
		this.importeSubTotal = importeSubTotal;
		this.envio = envio;
		this.productosCesta = productosCesta;
	}

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

	public Set<ProductoCesta> getProductosCesta() {
		return productosCesta;
	}

	public void setProductosCesta(Set<ProductoCesta> productosCesta) {
		this.productosCesta = productosCesta;
	}
	
}
