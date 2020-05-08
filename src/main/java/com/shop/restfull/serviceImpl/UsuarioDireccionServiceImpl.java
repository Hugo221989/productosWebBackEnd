package com.shop.restfull.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.UsuarioDireccion;
import com.shop.restfull.repository.UsuarioDireccionRepository;
import com.shop.restfull.service.IUsuarioDireccionService;
@Service
public class UsuarioDireccionServiceImpl implements IUsuarioDireccionService {
	
	@Autowired
	private UsuarioDireccionRepository usuarioDireccionRepository;

	@Override
	public List<UsuarioDireccion> obtenerDireccionesUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return this.usuarioDireccionRepository.findUsuarioDireccionByUsuarioId(idUsuario);
	}

	@Override
	public UsuarioDireccion crearUsuarioDireccion(UsuarioDireccion usuarioDireccion) {
		// TODO Auto-generated method stub
		return this.usuarioDireccionRepository.saveAndFlush(usuarioDireccion);
	}

	@Override
	public void eliminarDireccionesUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		this.usuarioDireccionRepository.deleteUsuarioDireccionByUsuarioId(idUsuario);
	}

}
