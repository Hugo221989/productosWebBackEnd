package com.shop.restfull.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
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
import com.shop.restfull.dto.ManagerMenuItemDto;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.service.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/manager")
@Api(value = "VALUE", description = "Panel Administración")
public class ManagerController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@ApiOperation(value = "Obtener información del menú superior en el panel de administración", notes = "Este servicio web obtiene los enlaces a las secciones del panel de administración.", response = ManagerMenuItemDto.class, responseContainer = "ManagerMenuItemDto")
	@GetMapping("/obtenerMenuPrincipal")
	public ResponseEntity<List<ManagerMenuItemDto>> obtenerMenuPrincipal(){
		
		ManagerMenuItemDto menuItem1 = new ManagerMenuItemDto();
		menuItem1.setNombre("Customers");
		Link selfLink1 = linkTo(methodOn(ManagerController.class).getCustomerBasicList()).withSelfRel();
		menuItem1.add(selfLink1);
		
		ManagerMenuItemDto menuItem2 = new ManagerMenuItemDto();
		menuItem2.setNombre("Products");
		Link selfLink2 = linkTo(methodOn(ProductoController.class).obtenerProductosList()).withSelfRel();
		menuItem2.add(selfLink2);
		
		ManagerMenuItemDto menuItem3 = new ManagerMenuItemDto();
		menuItem3.setNombre("Orders");
		Link selfLink3 = linkTo(methodOn(PedidoController.class).obtenerPedidos()).withSelfRel();
		menuItem3.add(selfLink3);
		
		List<ManagerMenuItemDto> managerMenuItems = new ArrayList<ManagerMenuItemDto>();
		managerMenuItems.add(menuItem1);
		managerMenuItems.add(menuItem2);
		managerMenuItems.add(menuItem3);
		
		return ResponseEntity.ok(managerMenuItems);
	}
	
	@ApiOperation(value = "Obtener lista de usuario en el panel de administración", notes = "Este servicio web obtiene los datos básicos de los usuarios.", response = CustomerBasicDto.class, responseContainer = "CustomerBasicDto")
	@GetMapping("/getCustomerBasicList")
	public ResponseEntity<List<CustomerBasicDto>> getCustomerBasicList(){
		
		List<CustomerBasicDto> customerBasicList;
		customerBasicList = usuarioService.obtenerCustomerBasicList();
		for(CustomerBasicDto customerBasicDto : customerBasicList) {
			Link selfLink = linkTo(methodOn(ManagerController.class).getCustomerBasic(String.valueOf(customerBasicDto.getId()))).withSelfRel();
			customerBasicDto.add(selfLink);
		}
		return ResponseEntity.ok(customerBasicList);
	}
	
	
	@ApiOperation(value = "Obtener el detalle de un usuario en el panel de administración", notes = "Este servicio web obtiene los datos básicos de un usuario.", response = CustomerDto.class, responseContainer = "CustomerDto")
	@GetMapping("/getCustomerBasic")
	public ResponseEntity<CustomerDto> getCustomerBasic(@ApiParam(value = "id", required = true) @RequestParam(required = true) String id){
		if(id == null)return ResponseEntity.ok(null);
		Integer customerId = Integer.parseInt(id);
		CustomerDto customer = usuarioService.obtenerCustomerBasic(customerId);
		
		Link selfLinkDeleteUser = linkTo(methodOn(ManagerController.class).deleteCustomer(customerId)).withRel("delete");
		customer.add(selfLinkDeleteUser);
		Link selfLinkEditUser = linkTo(methodOn(ManagerController.class).updateCustomer(customer)).withRel("update");
		customer.add(selfLinkEditUser);
		
		Link selfLinkGenders = linkTo(methodOn(UsuarioController.class).obtenerGeneros()).withRel("genders");
		customer.add(selfLinkGenders);
		
		return ResponseEntity.ok(customer);
	}
	
	@ApiOperation(value = "Editar usuario", notes = "Este servicio web edita los datos del usuario.", response = Void.class, responseContainer = "Void")
	@PutMapping("/updateCustomer")
	public ResponseEntity<Void> updateCustomer(@ApiParam(value = "customer", required = true) @RequestBody(required = true) CustomerDto customer){
		
		usuarioService.editarCustomer(customer);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Eliminar usuario", notes = "Este servicio web edita los datos del usuario.", response = Void.class, responseContainer = "Void")
	@PostMapping("/deleteCustomer")
	public ResponseEntity<Void> deleteCustomer(@ApiParam(value = "usuarioId", required = true) @RequestBody(required = true) Integer usuarioId){
		
		usuarioService.eliminarUsuario(usuarioId);
		return ResponseEntity.noContent().build();
	}
	
}
