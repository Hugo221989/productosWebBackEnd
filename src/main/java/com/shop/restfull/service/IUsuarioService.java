package com.shop.restfull.service;

import java.util.Optional;

import com.shop.restfull.model.Usuario;


public interface IUsuarioService {
	
	public Usuario crearUsuario(Usuario usuario);
	
	public Optional<Usuario> obtenerUsuario(String email);
	
	public void eliminarUsuario(Usuario usuario);
	
	public Usuario editarUsuario(Usuario usuario);

}
 