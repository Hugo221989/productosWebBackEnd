package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.Pedido;
import com.shop.restfull.repository.PedidoRepository;
import com.shop.restfull.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> obtenerPedidos(){
		return this.pedidoRepository.findAll();
	}

	@Override
	public List<Pedido> obtenerPedidosByUsuarioId(int idUsuario) {
		return this.pedidoRepository.findOrdersByUserId(idUsuario).orElse(null);
	}

}
