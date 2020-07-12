package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.MetodoEnvio;
import com.shop.restfull.repository.MetodoEnvioRepository;
import com.shop.restfull.service.IMetodoEnvioService;
@Service
public class MetodoEnvioServiceImpl implements IMetodoEnvioService {

	@Autowired
	private MetodoEnvioRepository metodoEnvioRepository;
	@Override
	public List<MetodoEnvio> obtenerMetodosEnvio() {
		// TODO Auto-generated method stub
		return this.metodoEnvioRepository.findAll();
	}

}
