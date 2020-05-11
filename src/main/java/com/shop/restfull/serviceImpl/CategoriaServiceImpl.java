package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.SubCategoria;
import com.shop.restfull.repository.CategoriaRepository;
import com.shop.restfull.repository.SubCategoriaRepository;
import com.shop.restfull.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	@Override
	public List<Categoria> obtenerCategorias() {
		// TODO Auto-generated method stub
		return this.categoriaRepository.findAll();
	}

	@Override
	public List<SubCategoria> obtenerSubCategoriasByCategoria(int idCategoria) {
		// TODO Auto-generated method stub
		return this.subCategoriaRepository.findSubCategoriaByCategoria(idCategoria);
	}

}
