package com.shop.restfull.service;

import java.util.List;

import com.shop.restfull.model.Pedido;

public interface IPedidoService {
	public List<Pedido> obtenerPedidos();
	public List<Pedido> obtenerPedidosByUsuarioId(int idUsuario);
}
