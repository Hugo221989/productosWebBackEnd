package com.shop.restfull.service;

import com.shop.restfull.model.Cesta;

public interface ICestaService {
	
	public Cesta obtenerCesta(int idUsuario);
	
	public Cesta addProducto(Cesta cesta);
	
	public Cesta eliminarProducto(Cesta cesta);

}
