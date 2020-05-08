package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.Sabor;
import com.shop.restfull.repository.SaborRepository;
import com.shop.restfull.service.ISaborService;
@Service
public class SaborServiceImpl implements ISaborService {

	@Autowired
	private SaborRepository saborRepository;
	
	@Override
	public List<Sabor> obtenerListaSabores() {
		// TODO Auto-generated method stub
		return this.saborRepository.findAll();
	}

}
