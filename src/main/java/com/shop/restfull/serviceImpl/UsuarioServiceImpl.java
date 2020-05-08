package com.shop.restfull.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.UsuarioRepository;
import com.shop.restfull.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> obtenerUsuario(String email) {
		// TODO Auto-generated method stub
		Optional<Usuario> usu = this.usuarioRepository.findByEmail(email);
		return usu;
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.usuarioRepository.delete(usuario);
	}

	@Override
	@Transactional
	@Modifying
	public Usuario editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return this.usuarioRepository.save(usuario);
	}

}
