package com.shop.restfull.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.dto.CestaDto;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.MetodoEnvio;
import com.shop.restfull.model.MetodoPago;
import com.shop.restfull.model.Pedido;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.producto.ProductoCesta;
import com.shop.restfull.service.ICestaService;
import com.shop.restfull.service.IMetodoEnvioService;
import com.shop.restfull.service.IMetodoPagoService;
import com.shop.restfull.service.IPedidoService;
import com.shop.restfull.service.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/pedido")
@Api(value = "VALUE", description = "Gestión Pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;
	@Autowired
	private IMetodoEnvioService metodoEnvioService;
	@Autowired
	private IMetodoPagoService metodoPagoService;
	@Autowired
	private ICestaService cestaService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@ApiOperation(value = "Obtener todos los pedidos realizados en la web", notes = "Este servicio web obtiene todos los pedidos.", response = Pedido.class, responseContainer = "Pedido")
	@GetMapping("/obtenerPedidos")
	public ResponseEntity <List<Pedido>> obtenerPedidos(){
		return ResponseEntity.ok(pedidoService.obtenerPedidos());
	}
	
	@ApiOperation(value = "Obtener pedidos de un usuario", notes = "Este servicio web obtiene los pedidos realizados por un usuario.", response = Pedido.class, responseContainer = "Pedido")
	@GetMapping("/obtenerPedidosByUsuarioId")
	public ResponseEntity <List<Pedido>> obtenerPedidosByUsuarioId(@ApiParam(value = "idUsuario", required = true) @RequestParam(required = true) int idUsuario){
		return ResponseEntity.ok(pedidoService.obtenerPedidosByUsuarioId(idUsuario));
	}
	
	@ApiOperation(value = "Obtener metodos de envio", notes = "Este servicio web obtiene los metodos de envio de un pedido.", response = MetodoEnvio.class, responseContainer = "MetodoEnvio")
	@GetMapping("/obtenerMetodosEnvio")
	public ResponseEntity <List<MetodoEnvio>> obtenerMetodosEnvio(){
		return ResponseEntity.ok(metodoEnvioService.obtenerMetodosEnvio());
	}
	
	@ApiOperation(value = "Obtener la lista de metodos de pago", notes = "Este servicio web obtiene el listado de medios de pago de un pedido.", response = MetodoPago.class, responseContainer = "MetodoPago")
	@GetMapping("/obtenerMetodosPago")
	public ResponseEntity <List<MetodoPago>> obtenerMetodosPago(){
		return ResponseEntity.ok(metodoPagoService.obtenerMetodospago());
	}
	
	@ApiOperation(value = "Guardar cesta de un usuario", notes = "Este servicio web guarda la cesta de la sesión del usuario.", response = Cesta.class, responseContainer = "Cesta")
	@PostMapping("/saveCesta")
	public ResponseEntity<Cesta> saveCesta(@ApiParam(value = "cesta", required = true) @RequestBody(required = false) Cesta cesta,
			@ApiParam(value = "email", required = true) @RequestParam(required = true) String email){
		Usuario usuario = this.usuarioService.obtenerUsuario(email).orElse(null);
		if(usuario != null) {
			Cesta cestaBd = this.cestaService.obtenerCesta(usuario.getUsuario());
			if(cestaBd != null) {
				Set<ProductoCesta> productoCesta = cestaBd.getProductosCesta();
				this.cestaService.removeProductoCesta(productoCesta);
				//this.cestaService.removeCesta(cestaBd);
			}
			//cestaBd.setUsuario(usuario);
			cesta.setUsuario(usuario);
			cesta.setId(cestaBd.getId());
			return ResponseEntity.ok(this.cestaService.saveCesta(cestaBd, cesta));
		}
		return ResponseEntity.ok(null);
	}
	@ApiOperation(value = "Obtener la cesta del usuario", notes = "Este servicio web obtiene cesta de la sesión del usuario.", response = Cesta.class, responseContainer = "Cesta")
	@GetMapping("/obtenerCesta")
	public ResponseEntity <CestaDto> obtenerCesta(@ApiParam(value = "username", required = true) @RequestParam(required = true) String username){
		Cesta cesta = this.cestaService.obtenerCesta(username);
		CestaDto cestaDto = this.cestaService.cestaEntityToDto(cesta);
		return ResponseEntity.ok(cestaDto);
	}
	
}
