package com.shop.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.producto.SubCategoria;

@Repository
@Transactional
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer>{
	@Transactional
	@Query("SELECT s FROM SubCategoria s WHERE s.categoria.id = ?1")
	public List<SubCategoria> findSubCategoriaByCategoria(int idCategoria);
}
