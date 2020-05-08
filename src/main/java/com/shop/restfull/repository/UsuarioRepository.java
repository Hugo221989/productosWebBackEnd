package com.shop.restfull.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	public Optional<Usuario> findByEmail(String email);

}
