package com.shop.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.producto.Producto;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	@Transactional
	@Query("SELECT p FROM Producto p")
	public Optional<List<Producto>> findRelatedProducts(Pageable pageable);
	
	@Transactional
	@Query("SELECT p FROM Producto p WHERE p.nombre LIKE CONCAT('%',?1,'%')")
	public Optional<List<Producto>> findProductsByBuscador(String buscador);
	
	@Transactional
	@Query("SELECT p FROM Producto p WHERE p.subCategoria.key = ?1")
	public Optional<List<Producto>> findProductsBySubCategoria(String subCategoria);
}
