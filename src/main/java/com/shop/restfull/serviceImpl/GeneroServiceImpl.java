package com.shop.restfull.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.Genero;
import com.shop.restfull.repository.GeneroRepository;
import com.shop.restfull.service.IGeneroService;
@Service
public class GeneroServiceImpl implements IGeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	@Override
	public List<Genero> obtenerListaGeneros() {
		// TODO Auto-generated method stub
		return this.generoRepository.findAll();
	}

	@Override
	public Optional<Genero> obtenerGeneroById(int id) {
		// TODO Auto-generated method stub
		return this.generoRepository.findById(id);
	}

}
