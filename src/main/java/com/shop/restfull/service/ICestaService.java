package com.shop.restfull.service;

import java.util.Set;

import com.shop.restfull.dto.CestaDto;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.producto.ProductoCesta;

public interface ICestaService {
	
	public Cesta obtenerCesta( String username);
	
	public CestaDto cestaEntityToDto(Cesta cesta);
	
	public Cesta addProducto(Cesta cesta);
	
	public void eliminarCestaUsuario(int idUsuario);
	
	public void removeCesta(Cesta cesta);
	
	public void removeProductoCesta(Set<ProductoCesta> productoCesta);
	
	public Cesta saveCesta(Cesta cestaBd, Cesta cestaActualizada);
	
	public Set<ProductoCesta> createNewsProductosCesta(Cesta cestaActualizada);

}
