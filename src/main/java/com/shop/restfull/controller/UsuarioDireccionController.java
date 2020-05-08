package com.shop.restfull.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.UsuarioDireccion;
import com.shop.restfull.service.IUsuarioDireccionService;
import com.shop.restfull.service.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usuarioDireccion")
@Api(value = "VALUE", description = "Gestión Dirección Usuario")
public class UsuarioDireccionController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioDireccionService usuarioDireccionService;
	
	@ApiOperation(value = "Crear dirección usuario", notes = "Este servicio web crea los datos de la dirección del usuario.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/crearUsuarioDireccion")
	public ResponseEntity<Usuario> crearUsuarioDireccion(@ApiParam(value = "email", required = true) @RequestParam(required = true) String email, 
			@ApiParam(value = "UsuarioDireccion", required = true) @RequestBody(required = true) UsuarioDireccion usuarioDireccionInput){
		
		Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		
		//List<UsuarioDireccion> usuarioDireccionList = usuarioDireccionService.obtenerDireccionesUsuario(usuario.getId());
//		usuarioDireccionList = usuario.getDireccion();
//		usuarioDireccionList.clear();
//		usuarioDireccionList.add(usuarioDireccionInput);
		//usuario.addToDirection(usuarioDireccionInput);
//		usuario.setDireccion(usuarioDireccionList);
		usuarioDireccionService.eliminarDireccionesUsuario(usuario.getId());
		usuarioDireccionInput.setUsuario(usuario);
		usuarioDireccionService.crearUsuarioDireccion(usuarioDireccionInput);
		Usuario usuarioReturn = usuarioService.obtenerUsuario(email).orElse(null);
		
		return ResponseEntity.ok(usuarioReturn);
	}

}
