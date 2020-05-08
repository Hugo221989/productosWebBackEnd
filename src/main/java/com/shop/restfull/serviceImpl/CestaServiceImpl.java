package com.shop.restfull.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.repository.CestaRepository;
import com.shop.restfull.service.ICestaService;

@Service
public class CestaServiceImpl implements ICestaService {
	
	@Autowired
	private CestaRepository cestaRepository;

	@Override
	public Cesta obtenerCesta(int idUsuario) {
		// TODO Auto-generated method stub
		return this.cestaRepository.findCestaByUserId(idUsuario).orElse(null);
	}

	@Override
	public Cesta addProducto(Cesta cesta) {
		// TODO Auto-generated method stub
		return this.cestaRepository.save(cesta);
	}

	@Override
	public Cesta eliminarProducto(Cesta cesta) {
		// TODO Auto-generated method stub
		return this.cestaRepository.save(cesta);
	}

}
