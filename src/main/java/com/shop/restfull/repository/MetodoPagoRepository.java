package com.shop.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.MetodoPago;

@Repository
@Transactional
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{

}
