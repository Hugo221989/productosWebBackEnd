package com.shop.restfull.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.payload.request.LoginRequest;
import com.shop.restfull.payload.request.SignupRequest;
import com.shop.restfull.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "VALUE", description = "Gestión Login")
public class LoginController {

	@Autowired
	private ILoginService loginService;	
	
	
	@ApiOperation(value = "Login usuario", notes = "Este servicio web logea al usuario mediante token JWT.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return this.loginService.authenticateUser(loginRequest);
	}

	@ApiOperation(value = "Registro usuario", notes = "Este servicio web registra al usuario.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) {
		
		try {
			return this.loginService.registerUser(signUpRequest, request );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@ApiOperation(value = "Obtener generos", notes = "Este servicio web comprueba el token enviado al usuario y lo valida para activar su cuenta.", response = Usuario.class, responseContainer = "Usuario")
	@GetMapping("/activarUsuarioRegistrado")
	public ResponseEntity<Usuario> activarUsuarioRegistrado(@ApiParam(value = "token", required = true) @RequestParam(required = true) String token){
		
		return this.loginService.activateUserByToken(token);
	}
}
