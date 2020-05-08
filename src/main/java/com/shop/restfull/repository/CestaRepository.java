package com.shop.restfull.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.Cesta;


@Repository
@Transactional
public interface CestaRepository extends JpaRepository<Cesta, Integer>{
	@Transactional
	@Query("SELECT c FROM Cesta c WHERE c.usuario.id = ?1")
	public Optional<Cesta> findCestaByUserId(int idUsuario);
}
