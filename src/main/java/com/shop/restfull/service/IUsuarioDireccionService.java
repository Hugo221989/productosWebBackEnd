package com.shop.restfull.service;

import java.util.List;

import com.shop.restfull.model.UsuarioDireccion;

public interface IUsuarioDireccionService {
	
	public List<UsuarioDireccion> obtenerDireccionesUsuario(int idUsuario);
	
	public void eliminarDireccionesUsuario(int idUsuario);
	
	public UsuarioDireccion crearUsuarioDireccion(UsuarioDireccion usuarioDireccion);

}
