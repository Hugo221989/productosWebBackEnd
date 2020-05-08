package com.shop.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.Genero;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.UsuarioDireccion;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.service.ICestaService;
import com.shop.restfull.service.IGeneroService;
import com.shop.restfull.service.IProductoService;
import com.shop.restfull.service.IUsuarioDireccionService;
import com.shop.restfull.service.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usuario")
@Api(value = "VALUE", description = "Gestión Usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private ICestaService cestaService;
	@Autowired
	private IGeneroService generoService;
	@Autowired
	private IUsuarioDireccionService usuarioDireccionService;
	@Autowired
	private IProductoService productoService;
	
	@ApiOperation(value = "Obtener usuario", notes = "Este servicio web obtiene los datos del usuario.", response = Usuario.class, responseContainer = "Usuario")
	@GetMapping("/obtenerUsuario")
	public ResponseEntity<Usuario> obtenerUsuario(@ApiParam(value = "email", required = true) @RequestParam(required = true) String email){
		
		Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		return ResponseEntity.ok(usuario);
	}
	
	@ApiOperation(value = "Crear usuario", notes = "Este servicio web crea los datos del usuario.", response = Usuario.class, responseContainer = "Usuario")
	@PostMapping("/crearUsuario")
	public ResponseEntity<Usuario> crearUsuario(@ApiParam(value = "Usuario", required = true) @RequestBody(required = true) Usuario usuarioInput){
		
		Usuario usuario = usuarioService.crearUsuario(usuarioInput);
		return ResponseEntity.ok(usuario);
	}
	
	@ApiOperation(value = "Editar usuario", notes = "Este servicio web edita los datos del usuario.", response = Usuario.class, responseContainer = "Usuario")
	@PutMapping("/editarUsuario")
	public ResponseEntity<Usuario> editarUsuario(@ApiParam(value = "Usuario", required = true) @RequestBody(required = true) Usuario usuarioInput){
		
		Usuario usuario = usuarioService.editarUsuario(usuarioInput);
		return ResponseEntity.ok(usuario);
	}
	
	@ApiOperation(value = "Editar usuario", notes = "Este servicio web edita los datos del usuario.", response = Void.class, responseContainer = "Usuario")
	@PostMapping("/eliminarUsuario")
	public ResponseEntity<Void> eliminarUsuario(@ApiParam(value = "Usuario", required = true) @RequestBody(required = true) Usuario usuarioInput){
		
		usuarioService.eliminarUsuario(usuarioInput);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Obtener generos", notes = "Este servicio web obtiene los diferentes generos.", response = Genero.class, responseContainer = "Usuario")
	@GetMapping("/obtenerGeneros")
	public ResponseEntity<List<Genero>> obtenerGeneros(){
		
		return ResponseEntity.ok(generoService.obtenerListaGeneros());
	}
	
	@ApiOperation(value = "Obtener cesta", notes = "Este servicio web obtiene la cesta del usuario.", response = Cesta.class, responseContainer = "Usuario")
	@GetMapping("/obtenerCesta")
	public ResponseEntity<Cesta> obtenerCesta(@ApiParam(value = "email", required = true) @RequestParam(required = true) String email){
		
		Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		
		return ResponseEntity.ok(cestaService.obtenerCesta(usuario.getId()));
	}
	
	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene los productos.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductos")
	public ResponseEntity<List<Producto>> obtenerProductos(){
		
		//Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		
		return ResponseEntity.ok(productoService.obtenerListaProductos());
	}
	
	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene los productos.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductoById")
	public ResponseEntity <Producto> obtenerProductoById(@ApiParam(value = "idProducto", required = true) @RequestParam(required = true) int idProducto){
		return ResponseEntity.ok(productoService.obtenerProductoById(idProducto).orElse(null));
	}
	
	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene las direcciones del usuario.", response = UsuarioDireccion.class, responseContainer = "Usuario")
	@GetMapping("/obtenerDireccionesUsuario")
	public ResponseEntity<List<UsuarioDireccion>> obtenerDireccionesUsuario(@ApiParam(value = "email", required = true) @RequestParam(required = true) String email){
		
		Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		
		return ResponseEntity.ok(usuarioDireccionService.obtenerDireccionesUsuario(usuario.getId()));
	}
	
	

}
