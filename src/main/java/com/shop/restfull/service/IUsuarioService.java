package com.shop.restfull.service;

import java.util.List;
import java.util.Optional;
import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.dto.CustomerDto;
import com.shop.restfull.model.Usuario;


public interface IUsuarioService {
	
	public Usuario crearUsuario(Usuario usuario);
	
	public Optional<Usuario> obtenerUsuario(String email);
	
	public void eliminarUsuario(Integer usuarioId);
	
	public Usuario editarUsuario(Usuario usuario);
	
	public CustomerDto editarCustomer(CustomerDto customer);
	
	public List<CustomerBasicDto> obtenerCustomerBasicList();
	
	public CustomerDto obtenerCustomerBasic(Integer customerId);

}
 