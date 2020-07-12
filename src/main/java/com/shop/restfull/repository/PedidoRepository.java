package com.shop.restfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.Pedido;

@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	@Query("SELECT p FROM Pedido p WHERE p.usuario.id = ?1")
	public Optional<List<Pedido>> findOrdersByUserId(int idUsuario);
}
