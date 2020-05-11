package com.shop.restfull.service;

import java.util.List;

import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.SubCategoria;

public interface ICategoriaService {
	
	public List<Categoria> obtenerCategorias();
	
	public List<SubCategoria> obtenerSubCategoriasByCategoria(int idCategoria);

}
