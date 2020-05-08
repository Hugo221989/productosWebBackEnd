package com.shop.restfull.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.restfull.model.ERole;
import com.shop.restfull.model.Role;

@Repository
public interface RoleLoginRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
