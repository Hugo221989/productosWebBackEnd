package com.shop.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.producto.Categoria;

@Repository
@Transactional
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	@Transactional
	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre.id = ?1")
	public Optional<List<Categoria>> findCategoriasByCategoriaPadre(int categoriaPadre);
}
