package com.shop.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.producto.ProductoCesta;

@Repository
@Transactional
public interface ProductoCestaRepository  extends JpaRepository<ProductoCesta, Integer>{

}
