package com.shop.restfull.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.dto.CustomerDto;
import com.shop.restfull.model.Genero;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.UsuarioDireccion;
import com.shop.restfull.service.IGeneroService;
import com.shop.restfull.service.IUsuarioDireccionService;
import com.shop.restfull.service.IUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usuario")
@Api(value = "VALUE", description = "Gestión Usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IGeneroService generoService;
	@Autowired
	private IUsuarioDireccionService usuarioDireccionService;
	
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
	public ResponseEntity<Usuario> editarUsuario(@ApiParam(value = "user", required = true) @RequestBody(required = true) Usuario user){
		
		Usuario usuario = usuarioService.editarUsuario(user);
		return ResponseEntity.ok(usuario);
	}
	
	@ApiOperation(value = "Editar usuario", notes = "Este servicio web edita los datos del usuario.", response = Void.class, responseContainer = "Void")
	@PostMapping("/eliminarUsuario")
	public ResponseEntity<Void> eliminarUsuario(@ApiParam(value = "usuarioId", required = true) @RequestBody(required = true) Integer usuarioId){
		
		usuarioService.eliminarUsuario(usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Obtener generos", notes = "Este servicio web obtiene los diferentes generos.", response = Genero.class, responseContainer = "Genero")
	@GetMapping("/obtenerGeneros")
	public ResponseEntity<List<Genero>> obtenerGeneros(){
		
		return ResponseEntity.ok(generoService.obtenerListaGeneros());
	}

	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene las direcciones del usuario.", response = UsuarioDireccion.class, responseContainer = "UsuarioDireccion")
	@GetMapping("/obtenerDireccionesUsuario")
	public ResponseEntity<List<UsuarioDireccion>> obtenerDireccionesUsuario(@ApiParam(value = "email", required = true) @RequestParam(required = true) String email){
		
		Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		
		return ResponseEntity.ok(usuarioDireccionService.obtenerDireccionesUsuario(usuario.getId()));
	}
	
//	@ApiOperation(value = "Obtener el detalle de un usuario en el panel de administración", notes = "Este servicio web obtiene los datos básicos de un usuario.", response = CustomerBasicDto.class, responseContainer = "CustomerBasicDto")
//	@GetMapping("/obtenerCustomerBasic")
//	public ResponseEntity<CustomerBasicDto> obtenerCustomerBasic(@ApiParam(value = "id", required = true) @RequestParam(required = true) String id){
//		if(id == null)return ResponseEntity.ok(null);
//		Integer customerId = Integer.parseInt(id);
//		Usuario usuario = usuarioService.obtenerCustomerBasic(customerId);
//		CustomerBasicDto customerBasicDto = new CustomerBasicDto();
//		customerBasicDto.setId(usuario.getId());
//		customerBasicDto.setNombre(usuario.getNombre());
//		customerBasicDto.setApellido(usuario.getApellido());
//		customerBasicDto.setEmail(usuario.getEmail());
//		customerBasicDto.setUsuario(usuario.getUsuario());
//		return ResponseEntity.ok(customerBasicDto);
//	}


}
