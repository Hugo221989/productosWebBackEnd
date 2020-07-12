package com.shop.restfull.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.shop.restfull.dto.CatProductoDto;
import com.shop.restfull.dto.ProductoDto;
import com.shop.restfull.model.producto.Producto;

public interface IProductoService {
	
	public List<ProductoDto> obtenerListaProductos();
	public List<ProductoDto> obtenerListaProductosBuscador(String buscador);
	public List<ProductoDto> obtenerListaProductosByCategoria(String categoria);
	public List<ProductoDto> obtenerListaProductosBySubCategoria(String subCategoria);
	public List<ProductoDto> obtenerListaProductosByCategoriaPadre(String categoriaPadre);
	public List<ProductoDto> obtenerListaProductosRelacionadosLimit5(Pageable pageable, String categoriaPadreKey);
	
	public Producto obtenerProductoById(int idProducto);
	
	public CatProductoDto obtenerCatSubCatProducto( int idProducto);
	
	public CatProductoDto fillCatProductoDtoFromProducto(Producto prod);
	

}
