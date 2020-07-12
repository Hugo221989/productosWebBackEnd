package com.shop.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.dto.CatProductoDto;
import com.shop.restfull.dto.ProductoDto;
import com.shop.restfull.model.producto.Producto;
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
	
	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene los productos.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductoById")
	public ResponseEntity <Producto> obtenerProductoById(@ApiParam(value = "idProducto", required = true) @RequestParam(required = true) int idProducto){
		return ResponseEntity.ok(productoService.obtenerProductoById(idProducto));
	}
	
	@ApiOperation(value = "Obtener productos", notes = "Este servicio web obtiene los productos.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductos")
	public ResponseEntity<List<ProductoDto>> obtenerProductos(@ApiParam(value = "buscador", required = false) @RequestParam(required = false) String buscador){
		
		//Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		if(buscador != null && !buscador.equals("null") && !buscador.trim().equals("")) {
			return ResponseEntity.ok(productoService.obtenerListaProductosBuscador(buscador));
		}
		return ResponseEntity.ok(productoService.obtenerListaProductos());
	}
	
	@ApiOperation(value = "Obtener productos por categoria Padre", notes = "Este servicio web obtiene los productos filtrados por categoriaPadre.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductosByCategoriaPadre")
	public ResponseEntity<List<ProductoDto>> obtenerProductosByCategoriaPadre(@ApiParam(value = "categoriaPadre", required = true) @RequestParam(required = true) String categoriaPadre){
		
		//Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		if(categoriaPadre != null) {
			return ResponseEntity.ok(productoService.obtenerListaProductosByCategoriaPadre(categoriaPadre));
		}
		return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value = "Obtener productos por categoria", notes = "Este servicio web obtiene los productos filtrados por categoria.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductosByCategoria")
	public ResponseEntity<List<ProductoDto>> obtenerProductosByCategoria(@ApiParam(value = "categoria", required = false) @RequestParam(required = false) String categoria){
		
		//Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		if(categoria != null && !categoria.equals("null") && !categoria.trim().equals("")) {
			return ResponseEntity.ok(productoService.obtenerListaProductosByCategoria(categoria));
		}
		return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value = "Obtener productos por subcategoria", notes = "Este servicio web obtiene los productos filtrados por subcategoria.", response = Producto.class, responseContainer = "Usuario")
	@GetMapping("/obtenerProductosBySubCategoria")
	public ResponseEntity<List<ProductoDto>> obtenerProductosBySubCategoria(@ApiParam(value = "subCategoria", required = false) @RequestParam(required = false) String subCategoria){
		
		//Usuario usuario = usuarioService.obtenerUsuario(email).orElse(null);
		if(subCategoria != null && !subCategoria.equals("null") && !subCategoria.trim().equals("")) {
			return ResponseEntity.ok(productoService.obtenerListaProductosBySubCategoria(subCategoria));
		}
		return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value = "Obtener productos relacionados", notes = "Este servicio web obtiene productos relacionados de un producto.", response = Producto.class, responseContainer = "Producto")
	@GetMapping("/obtenerProductosRelacionados")
	public ResponseEntity<List<ProductoDto>> obtenerProductosRelacionados(@ApiParam(value = "categoriaPadreKey", required = true) @RequestParam(required = true) String categoriaPadreKey){
		
		Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
		return ResponseEntity.ok(productoService.obtenerListaProductosRelacionadosLimit5(pageable, categoriaPadreKey));
	}
	
	
	@ApiOperation(value = "Obtener categoria y subcategoria de un producto", notes = "Este servicio web obtiene categoria y subcategoria de un producto.", response = CatProductoDto.class, responseContainer = "CatProductoDto")
	@GetMapping("/obtenerCatAndSubCatByProductoId")
	public ResponseEntity<CatProductoDto> obtenerCatAndSubCatByProductoId(@ApiParam(value = "idProducto", required = true) @RequestParam(required = true) int idProducto){
		
		return ResponseEntity.ok(productoService.obtenerCatSubCatProducto(idProducto));
	}
	
}
