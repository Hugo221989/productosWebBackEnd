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
}
