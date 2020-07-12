package com.shop.restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.model.RegistrationToken;

@Repository
@Transactional
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {
	RegistrationToken findByToken(String token);
}
