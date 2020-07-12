package com.shop.restfull.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.dto.CategoriaDto;
import com.shop.restfull.dto.CategoriaPadreDto;
import com.shop.restfull.dto.SubCategoriaDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.model.producto.SubCategoria;
import com.shop.restfull.repository.CategoriaPadreRepository;
import com.shop.restfull.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	private CategoriaPadreRepository categoriaPadreRepository;
	
	List<CategoriaDto> categorias;
	List<SubCategoriaDto> subCategorias;

	@Override
	public CategoriaPadreDto obtenerProductosByCategoriaPadre(String keyCategoriaPadre) {
		CategoriaPadre categoriaPadre = this.categoriaPadreRepository.findCategoriaPadreByKey(keyCategoriaPadre).orElse(null);
		return this.fillCategoriaPadreDto(categoriaPadre);
	}
	@Override
	public CategoriaPadreDto fillCategoriaPadreDto(CategoriaPadre categoriaPadre) {
		categorias = new ArrayList<>();
		
		if(categoriaPadre == null)return null;
		CategoriaPadreDto categoriaPadreReturn = new CategoriaPadreDto();
		categoriaPadreReturn.setId(categoriaPadre.getId());
		categoriaPadreReturn.setKey(categoriaPadre.getKey());
		categoriaPadreReturn.setModulo(categoriaPadre.getModulo());
		categoriaPadreReturn.setNombre(categoriaPadre.getNombre());
		categoriaPadreReturn.setNombreEng(categoriaPadre.getNombreEng());
		for(Categoria cat: categoriaPadre.getCategoria()) {
			CategoriaDto categoria = this.fillCategoriaDto(cat);
			categorias.add(categoria);
		}
		categoriaPadreReturn.setCategorias(categorias);
		return categoriaPadreReturn;
	}
	@Override
	public CategoriaDto fillCategoriaDto(Categoria cat) {
		subCategorias = new ArrayList<>();
		
		if(cat == null)return null;
		CategoriaDto categoria = new CategoriaDto();
		categoria.setId(cat.getId());
		categoria.setKey(cat.getKey());
		categoria.setNombre(cat.getNombre());
		categoria.setNombreEng(cat.getNombreEng());
		subCategorias = new ArrayList<>();
		for(SubCategoria subCat: cat.getSubCategoria()) {
			SubCategoriaDto subCategoria = this.fillSubCategoriaDto(subCat);
			subCategorias.add(subCategoria);
		}
		
		categoria.setSubcategorias(subCategorias);
		return categoria;
	}
	@Override
	public SubCategoriaDto fillSubCategoriaDto(SubCategoria subCat) {
		if(subCat == null)return null;
		SubCategoriaDto subCategoria = new SubCategoriaDto();
		subCategoria.setId(subCat.getId());
		subCategoria.setKey(subCat.getKey());
		subCategoria.setNombre(subCat.getNombre());
		subCategoria.setNombreEng(subCat.getNombreEng());
		return subCategoria;
	}

}
