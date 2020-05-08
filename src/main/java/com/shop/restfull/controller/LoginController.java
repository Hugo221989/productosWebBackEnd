package com.shop.restfull.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.ERole;
import com.shop.restfull.model.Genero;
import com.shop.restfull.model.Pedido;
import com.shop.restfull.model.Role;
import com.shop.restfull.model.User;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.UsuarioDireccion;
import com.shop.restfull.payload.request.LoginRequest;
import com.shop.restfull.payload.request.SignupRequest;
import com.shop.restfull.payload.response.JwtResponse;
import com.shop.restfull.payload.response.MessageResponse;
import com.shop.restfull.repository.RoleLoginRepository;
import com.shop.restfull.repository.UserLoginRepository;
import com.shop.restfull.service.IGeneroService;
import com.shop.restfull.service.IUsuarioService;
import com.shop.restfull.serviceImpl.UserDetailsImpl;
import com.shop.restfull.utils.JwtUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "VALUE", description = "Gestión Login")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserLoginRepository userRepository;

	@Autowired
	RoleLoginRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private IUsuarioService usuarioService;	
	
	@Autowired
	private IGeneroService generoService;
	
	@ApiOperation(value = "Login usuario", notes = "Este servicio web logea al usuario mediante token JWT.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		if(loginRequest.isRememberLogin()) {
			User user = this.userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
			if(user != null && user.isRememberLogin() != loginRequest.isRememberLogin()) {
				this.userRepository.save(user);
			}
			
		}

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@ApiOperation(value = "Registro usuario", notes = "Este servicio web registra al usuario.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		Usuario usuarioApp = new Usuario();
		usuarioApp.setAdmin(false);
		usuarioApp.setUsuario(user.getUsername());
		usuarioApp.setEmail(user.getEmail());
//		usuarioApp.setCesta(new Cesta());
//		Genero genero = generoService.obtenerGeneroById(3).orElse(null);
//		usuarioApp.setGenero(genero);
//		List<UsuarioDireccion> usuarioDireccionList = new ArrayList();
//		usuarioApp.setDireccion(usuarioDireccionList);
//		List<Pedido> pedidosList = new ArrayList();
//		usuarioApp.setPedidos(pedidosList);
		usuarioService.crearUsuario(usuarioApp);
		

		return ResponseEntity.ok(usuarioApp);
	}
}
