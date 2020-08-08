package com.shop.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.dto.CustomerDto;
import com.shop.restfull.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	final String CONSULTA_CUSTOMER =  "SELECT new com.shop.restfull.dto.CustomerDto(u.id, u.nombre, u.apellido, u.usuario, u.nacimiento, u.email, u.telefono, "
			+ "u.admin, genero, direccion ) "
			+ "FROM Usuario u "
			+ "LEFT JOIN Genero genero ON u.genero.id = genero.id "
			+ "LEFT JOIN UsuarioDireccion direccion ON u.direccion.id = direccion.id "
			+ "WHERE u.id = ?1";
	
	final String CONSULTA_CUSTOMER_BASIC_LIST =  "SELECT new com.shop.restfull.dto.CustomerBasicDto(u.id, u.nombre, u.apellido, u.usuario, u.email) "
			+ "FROM Usuario u";
	
	@Transactional
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	public Optional<Usuario> findByEmail(String email);
	
	@Query(CONSULTA_CUSTOMER_BASIC_LIST)
	public List<CustomerBasicDto> findCustomersBasic();
	
	@Query(CONSULTA_CUSTOMER)
	public CustomerDto findCustomer(Integer customerId);

}
