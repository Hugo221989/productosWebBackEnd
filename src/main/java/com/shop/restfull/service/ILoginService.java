package com.shop.restfull.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.shop.restfull.model.Usuario;
import com.shop.restfull.payload.request.LoginRequest;
import com.shop.restfull.payload.request.SignupRequest;

public interface ILoginService {
	
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest, HttpServletRequest request) throws SQLException;
	
	public ResponseEntity<Usuario> activateUserByToken(String token);
}
