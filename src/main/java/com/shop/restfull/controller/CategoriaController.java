package com.shop.restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.SubCategoria;
import com.shop.restfull.service.ICategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/categoria")
@Api(value = "VALUE", description = "Gestión Productos")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@ApiOperation(value = "Obtener categorias", notes = "Este servicio web obtiene las diferentes categorias de los productos.", response = Categoria.class, responseContainer = "Categoria")
	@GetMapping("/obtenerCategorias")
	public ResponseEntity<List<Categoria>> obtenerCategorias(){
		
		List<Categoria> categorias = this.categoriaService.obtenerCategorias();
		
		return ResponseEntity.ok(categorias);
	}
	
	@ApiOperation(value = "Obtener subcategorias", notes = "Este servicio web obtiene las diferentes subcategorias de los de una categoria.", response = SubCategoria.class, responseContainer = "SubCategoria")
	@GetMapping("/obtenerSubCategoriasByCategoria")
	public ResponseEntity<List<SubCategoria>> obtenerSubCategoriasByCategoria(@ApiParam(value = "idCategoria", required = true) @RequestParam(required = true) int idCategoria){
		
		List<SubCategoria> subCategorias = this.categoriaService.obtenerSubCategoriasByCategoria(idCategoria);
		
		return ResponseEntity.ok(subCategorias);
	}

}
