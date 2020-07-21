package com.shop.restfull.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.producto.CategoriaPadre;


@Repository
@Transactional
public interface CategoriaPadreRepository extends JpaRepository<CategoriaPadre, Integer>{
	@Transactional
	@Query("SELECT c FROM CategoriaPadre c WHERE c.kkey = ?1")
	public Optional<CategoriaPadre> findCategoriaPadreByKey(String categoriaPadreKey);
}
