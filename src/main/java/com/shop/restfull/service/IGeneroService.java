package com.shop.restfull.service;

import java.util.List;
import java.util.Optional;

import com.shop.restfull.model.Genero;


public interface IGeneroService {

	public List<Genero> obtenerListaGeneros();
	
	public Optional<Genero> obtenerGeneroById(int id);
	
}
