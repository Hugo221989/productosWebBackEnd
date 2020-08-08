package com.shop.restfull.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.dto.CustomerDto;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.UsuarioRepository;
import com.shop.restfull.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> obtenerUsuario(String email) {
		Optional<Usuario> usu = this.usuarioRepository.findByEmail(email);
		return usu;
	}

	@Override
	public void eliminarUsuario(Integer usuarioId) {
		this.usuarioRepository.deleteById(usuarioId);
	}

	@Override
	@Transactional
	@Modifying
	public Usuario editarUsuario(Usuario user) {
		return this.usuarioRepository.save(user);
	}
	
	@Override
	@Transactional
	@Modifying
	public CustomerDto editarCustomer(CustomerDto customer) {
		Usuario usuario = this.customerToUsuario(customer);
		this.usuarioRepository.save(usuario);
		return customer;
	}
	
	@Override
	public List<CustomerBasicDto> obtenerCustomerBasicList(){
		return this.usuarioRepository.findCustomersBasic();
	}
	
	@Override
	public CustomerDto obtenerCustomerBasic(Integer customerId) {
		return this.usuarioRepository.findCustomer(customerId);
	}
	
	private Usuario customerToUsuario(CustomerDto customer) {
		Usuario usuario = new Usuario();
		usuario.setId(customer.getId());
		usuario.setNombre(customer.getNombre());
		usuario.setApellido(customer.getApellido());
		usuario.setEmail(customer.getEmail());
		usuario.setNacimiento(customer.getNacimiento());
		usuario.setUsuario(customer.getUsuario());
		usuario.setTelefono(customer.getTelefono());
		usuario.setAdmin(customer.getAdmin());
		usuario.setGenero(customer.getGenero());
		usuario.setDireccion(customer.getDireccion());
		return usuario;
	}
	

}
