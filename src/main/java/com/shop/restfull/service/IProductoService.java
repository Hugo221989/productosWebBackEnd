package com.shop.restfull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.shop.restfull.model.producto.Producto;

public interface IProductoService {
	
	public List<Producto> obtenerListaProductos();
	public List<Producto> obtenerListaProductosLimit5(Pageable pageable);
	
	public Optional<Producto> obtenerProductoById(int idProducto);
	

}
