package com.shop.restfull.service;

import com.shop.restfull.dto.CategoriaDto;
import com.shop.restfull.dto.CategoriaPadreDto;
import com.shop.restfull.dto.SubCategoriaDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.model.producto.SubCategoria;

public interface ICategoriaService {
	
	CategoriaPadreDto obtenerProductosByCategoriaPadre(String keyCategoriaPadre);
	CategoriaPadreDto fillCategoriaPadreDto(CategoriaPadre categoriaPadre);
	CategoriaDto fillCategoriaDto(Categoria cat);
	SubCategoriaDto fillSubCategoriaDto(SubCategoria subCat);
}
