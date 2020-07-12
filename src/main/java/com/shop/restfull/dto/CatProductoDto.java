package com.shop.restfull.dto;

import java.io.Serializable;

public class CatProductoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idProducto;
	String categoriaPadreNombre;
	String categoriaPadreKey;
	Integer categoriaPadreId;
	String categoriaPadreModulo;
	String categoriaNombre;
	String categoriaKey;
	String subCategoriaNombre;
	String subCategoriaKey;
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	public String getCategoriaKey() {
		return categoriaKey;
	}
	public void setCategoriaKey(String categoriaKey) {
		this.categoriaKey = categoriaKey;
	}
	public String getSubCategoriaNombre() {
		return subCategoriaNombre;
	}
	public void setSubCategoriaNombre(String subCategoriaNombre) {
		this.subCategoriaNombre = subCategoriaNombre;
	}
	public String getSubCategoriaKey() {
		return subCategoriaKey;
	}
	public void setSubCategoriaKey(String subCategoriaKey) {
		this.subCategoriaKey = subCategoriaKey;
	}
	public String getCategoriaPadreNombre() {
		return categoriaPadreNombre;
	}
	public void setCategoriaPadreNombre(String categoriaPadreNombre) {
		this.categoriaPadreNombre = categoriaPadreNombre;
	}
	public String getCategoriaPadreKey() {
		return categoriaPadreKey;
	}
	public void setCategoriaPadreKey(String categoriaPadreKey) {
		this.categoriaPadreKey = categoriaPadreKey;
	}
	public String getCategoriaPadreModulo() {
		return categoriaPadreModulo;
	}
	public void setCategoriaPadreModulo(String categoriaPadreModulo) {
		this.categoriaPadreModulo = categoriaPadreModulo;
	}
	public Integer getCategoriaPadreId() {
		return categoriaPadreId;
	}
	public void setCategoriaPadreId(Integer categoriaPadreId) {
		this.categoriaPadreId = categoriaPadreId;
	}
	
	
}
