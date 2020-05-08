package com.shop.restfull.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.UsuarioDireccion;

@Repository
@Transactional
public interface UsuarioDireccionRepository extends JpaRepository<UsuarioDireccion, Integer>{
	@Modifying
	@Query("SELECT u FROM UsuarioDireccion u where u.usuario.id = ?1")
	List<UsuarioDireccion> findUsuarioDireccionByUsuarioId(int idUsuario);
	
	@Modifying
	@Query("DELETE FROM UsuarioDireccion u where u.usuario.id = ?1")
	void deleteUsuarioDireccionByUsuarioId(int idUsuario);
}
