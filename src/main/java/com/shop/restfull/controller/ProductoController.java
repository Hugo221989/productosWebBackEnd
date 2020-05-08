package com.shop.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.service.ICestaService;
import com.shop.restfull.service.IProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/producto")
@Api(value = "VALUE", description = "Gestión Productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ICestaService cestaService;
	
	@ApiOperation(value = "Obtener productos relacionados", notes = "Este servicio web obtiene productos relacionados de un producto.", response = Producto.class, responseContainer = "Producto")
	@GetMapping("/obtenerProductosRelacionados")
	public ResponseEntity<List<Producto>> obtenerProductosRelacionados(){
		
		Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
		return ResponseEntity.ok(productoService.obtenerListaProductosLimit5(pageable));
	}
	
	@ApiOperation(value = "Añadir productos a la cesta", notes = "Este servicio web añade los productos seleccionados a la cesta del usuario.", response = Cesta.class, responseContainer = "Cesta")
	@PostMapping("/addProducto")
	public ResponseEntity<Cesta> addProducto(@ApiParam(value = "idUsuario", required = true) @RequestParam(required = true) int idUsuario, 
			@ApiParam(value = "Producto", required = true) @RequestBody(required = true) Producto productoInput){
		
		Cesta cesta = this.cestaService.obtenerCesta(idUsuario);
		cesta.addProductos(productoInput);
		return ResponseEntity.ok(this.cestaService.addProducto(cesta));
	}
	
}
