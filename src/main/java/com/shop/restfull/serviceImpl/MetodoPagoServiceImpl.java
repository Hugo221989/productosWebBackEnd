package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.MetodoPago;
import com.shop.restfull.repository.MetodoPagoRepository;
import com.shop.restfull.service.IMetodoPagoService;
@Service
public class MetodoPagoServiceImpl implements IMetodoPagoService{
	
	@Autowired
	private MetodoPagoRepository metodoPagoRepository;

	@Override
	public List<MetodoPago> obtenerMetodospago() {
		// TODO Auto-generated method stub
		return this.metodoPagoRepository.findAll();
	}

}
